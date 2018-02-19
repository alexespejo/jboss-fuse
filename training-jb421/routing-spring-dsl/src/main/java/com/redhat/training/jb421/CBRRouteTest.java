package com.redhat.training.jb421;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.PropertyInject;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.spi.BrowsableEndpoint;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.camel.test.spring.UseAdviceWith;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockftpserver.fake.FakeFtpServer;
import org.mockftpserver.fake.UserAccount;
import org.mockftpserver.fake.filesystem.DirectoryEntry;
import org.mockftpserver.fake.filesystem.Permissions;
import org.mockftpserver.fake.filesystem.UnixFakeFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "META-INF/spring/camel-context.html" })
@UseAdviceWith
public class CBRRouteTest {

	@PropertyInject("server.hostname")
	private String ftpHost;
	@PropertyInject("server.port")
	private int ftpPort;
	@PropertyInject("to.abc.username")
	private String abcUsername;
	@PropertyInject("to.orly.username")
	private String orlyUsername;
	@PropertyInject("to.namming.username")
	private String nammingUsername;

	@PropertyInject("to.abc.password")
	private String abcPassword;
	@PropertyInject("to.orly.password")
	private String orlyPassword;
	@PropertyInject("to.namming.password")
	private String nammingPassword;

	private static FakeFtpServer fakeFtpServer;
	private static org.mockftpserver.fake.filesystem.FileSystem fileSystem;

	@BeforeClass
	public static void overrideProperties() {
		System.setProperty("server.hostname", "localhost");
		System.setProperty("server.port", "9090");
	}

	@Before
	public void setUpClass() throws InterruptedException {
		fakeFtpServer = new FakeFtpServer();
		fakeFtpServer.setServerControlPort(ftpPort);
		fileSystem = new UnixFakeFileSystem();
		DirectoryEntry directoryEntry1 = new DirectoryEntry("/");
		directoryEntry1.setPermissions(new Permissions("rwxrwxrwx"));
		fileSystem.add(directoryEntry1);
		createUserAndHome(abcUsername, abcPassword);
		createUserAndHome(orlyUsername, orlyPassword);
		createUserAndHome(nammingUsername, nammingPassword);
		fakeFtpServer.setFileSystem(fileSystem);
		fakeFtpServer.start();
		do {
			Thread.sleep(500);
		} while (!fakeFtpServer.isStarted());
	}

	@After
	public void tearDownClass() {
		fakeFtpServer.stop();
	}

	private void createUserAndHome(String username, String password) {
		DirectoryEntry directoryEntry1 = new DirectoryEntry("/"+ username);
		directoryEntry1.setPermissions(new Permissions("rwxrwxrwx"));
		directoryEntry1.setOwner(username);
		directoryEntry1.setGroup(username);
		fileSystem.add(directoryEntry1);
		UserAccount userAccount = new UserAccount(username,password,"/"+username);
	}

	private static int ORDER_QTY = 6;
	private static int TIMEOUT = 6;

	@Autowired
	private CamelContext context;

	@Test
	public void testRedeliveryUsingInterception() throws Exception{
		ModelCamelContext modelContext = context.adapt(ModelCamelContext.class);
		RouteDefinition route = context.getRouteDefinition("_route1");
		route.adviceWith(modelContext, new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				interceptSendToEndpoint("ftp:*").process(new Processor() {
					private  boolean firstTime = true;
					@Override
					public void process(Exchange exchange) throws Exception {
						if(firstTime){
							System.err.println("** Simulating newtwork Error**");
							firstTime = false;
							throw new ConnectException("simulated network error");
						}
					}
				});
			}
		});
		
		context.start();
		sendOrders();
		
		NotifyBuilder builder = new NotifyBuilder(context).whenDone(ORDER_QTY).create();
		builder.matches(TIMEOUT,TimeUnit.SECONDS);
		
//		assertALlOrdersProcessed();
	}
	
	@EndpointInject(uri="ftp://{{server.hostname}}:{{server.port}}/?username={{to.abc.username}}&amp;password={{to.abc.password}}&amp;fileName=order-${header.orderId}.xml")
	private BrowsableEndpoint ftp1;
	@EndpointInject(uri="ftp://{{server.hostname}}:{{server.port}}/?username={{to.orly.username}}&amp;password={{to.orly.password}}&amp;fileName=order-${header.orderId}.xml")
	private BrowsableEndpoint ftp2;
	@EndpointInject(uri="ftp://{{server.hostname}}:{{server.port}}/?username={{to.namming.username}}&amp;password={{to.namming.password}}&amp;fileName=order-${header.orderId}.xml")
	private BrowsableEndpoint ftp3;
	
//	private void assertALlOrdersProcessed(){
//		assertTrue("3 orders where expected for ABC",ftp1.getExchanges().size());
//		assertTrue("2 orders where expected for Orly",ftp1.getExchanges().size());
//		assertTrue("1 orders where expected for namming",ftp1.getExchanges().size());
//	}
	
	public static String DATA_FOLDER = "../../data/orders";
	
	@Produce(uri="activemq:queue:orders")
	private ProducerTemplate template;
	
	public void sendOrders() throws IOException{
		File datafolder = new File(DATA_FOLDER);
		for(File file:datafolder.listFiles()){
			String xml = new String(Files.readAllBytes(Paths.get(file.toURI())));
			template.sendBody(xml);
		}
	}
}

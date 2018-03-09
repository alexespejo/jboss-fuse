package com.redhat.training.jb421;

import java.util.concurrent.TimeUnit;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Before;
import org.junit.Test;

public class RouteTransformTest extends CamelBlueprintTestSupport {

	@Override
	protected String getBlueprintDescriptor() {
		return "/OSGI-INF/blueprint/blueprint.xml";
	}

	@Produce(uri = "file:origin")
	protected ProducerTemplate orders;

	@EndpointInject(uri = "mock:direct:transformChars")
	protected MockEndpoint commaEndpoint;

	@EndpointInject(uri = "mock:direct:recipientList")
	protected MockEndpoint charsEndpoint;

	@EndpointInject(uri = "mock:ftp:infrastructure")
	protected MockEndpoint ftpEndpoint;

	@EndpointInject(uri = "mock:activemq:comics")
	protected MockEndpoint jmsEndpoint;

	@EndpointInject(uri = "mock:file:test")
	protected MockEndpoint fileEndpoint;

	// @Before
	// public void setUp() {
	// CamelBlueprintTestSupport.deleteDirectory("/origin");
	// }

	@Test
	public void testRouteWithComma() throws Exception {
		ModelCamelContext modelCamelContext = context.adapt(ModelCamelContext.class);

		NotifyBuilder builder = new NotifyBuilder(context).whenDone(1).create();

		commaEndpoint.expectedMessageCount(1);
		commaEndpoint.expectedBodiesReceived(CSV_ORDER_1_COMMA);

		modelCamelContext.getRouteDefinition("transformChars").adviceWith(modelCamelContext,
				new AdviceWithRouteBuilder() {

					@Override
					public void configure() throws Exception {
						interceptSendToEndpoint("direct:transformChars").skipSendToOriginalEndpoint()
								.to("mock:direct:transformChars");
					}
				});

		context.start();

		orders.sendBodyAndHeader(CSV_ORDER_1, "CamelFileName", "orders-v2-A1.csv");
		builder.matches(5, TimeUnit.SECONDS);

		commaEndpoint.assertIsSatisfied(5000); // MASIH ERROR DISINI
	}

	@Test
	public void testRouteFileChars() throws Exception {
		ModelCamelContext modelCamelContext = context.adapt(ModelCamelContext.class);
		
		NotifyBuilder builder = new NotifyBuilder(context).whenDone(1).create();

		charsEndpoint.expectedMessageCount(1);
		charsEndpoint.expectedBodiesReceived(CSV_ORDER_2_CHARS);
		
		modelCamelContext.getRouteDefinition("transformChars").adviceWith(modelCamelContext,
				new AdviceWithRouteBuilder() {

					@Override
					public void configure() throws Exception {
						interceptSendToEndpoint("direct:recipientList").skipSendToOriginalEndpoint()
								.to("mock:direct:recipientList");
					}
				});

		context.start();

		orders.sendBodyAndHeader(CSV_BODY_1, "CamelFileName", "orders-v2-A2.csv");
		builder.matches(5, TimeUnit.SECONDS);

		charsEndpoint.assertIsSatisfied(5000); // MASIH ERROR DISINI
	}

	@Test
	public void testRouteRecipientList() throws Exception {
		ModelCamelContext modelCamelContext = context.adapt(ModelCamelContext.class);

		modelCamelContext.getRouteDefinition("recipientList").adviceWith(modelCamelContext,
				new AdviceWithRouteBuilder() {
					@Override
					public void configure() throws Exception {
						interceptSendToEndpoint("ftp:infrastructure").skipSendToOriginalEndpoint()
								.to("mock:ftp:infrastructure");
						interceptSendToEndpoint("activemq:comics").skipSendToOriginalEndpoint()
								.to("mock:activemq:comics");
						interceptSendToEndpoint("file:test").skipSendToOriginalEndpoint().to("mock:file:test");
					}
				});
		ftpEndpoint.expectedMessageCount(2);
		ftpEndpoint.expectedBodiesReceived(CSV_BODY_1, CSV_BODY_3);
		jmsEndpoint.expectedMessageCount(1);
		jmsEndpoint.expectedBodiesReceived(CSV_BODY_2);

		NotifyBuilder builder = new NotifyBuilder(context).whenDone(1).create();

		context.start();

		orders.sendBodyAndHeader(CSV_ORDER_1, "CamelFileName", "orders-v2-A3.csv");
		builder.matches(5, TimeUnit.SECONDS);

		ftpEndpoint.assertIsSatisfied(5000);
		jmsEndpoint.assertIsSatisfied(5000);
	}

	private final static String CSV_ORDER_1 = "John Doe,11/10/16,123 Easy St,Anytown,AS,Çatßher in the Rye,1,14.00\n"
			+ "Amy Smith,10/31/16,2 Knightdown Ave,Carlsbad,CA,Gone wiœ the Wind,2,28.00\n"
			+ "Terry Jones,11/02/16,2 Baker St,Portland,OR,Death of a Salesman,1,15.00";
	private final static String CSV_ORDER_1_COMMA = "John Doe`11/10/16`123 Easy St`Anytown`AS`Çatßher in the Rye`1`14.00\n"
			+ "Amy Smith`10/31/16`2 Knightdown Ave`Carlsbad`CA`Gone wiœ the Wind`2`28.00\n"
			+ "Terry Jones`11/02/16`2 Baker St`Portland`OR`Death of a Salesman`1`15.00";

	private final static String CSV_ORDER_2_CHARS = "John Doe`11/10/16`123 Easy St`Anytown`AS`?at?her in the Rye`1`14.00\n"
			+ "Amy Smith`10/31/16`2 Knightdown Ave`Carlsbad`CA`Gone wi? the Wind`2`28.00\n"
			+ "Terry Jones`11/02/16`2 Baker St`Portland`OR`Death of a Salesman`1`15.00";
	private final static String CSV_BODY_1 = "John Doe`11/10/16`123 Easy St`Anytown`AS`?at?her in the Rye`1`14.00";
	private final static String CSV_BODY_2 = "Amy Smith`10/31/16`2 Knightdown Ave`Carlsbad`CA`Gone wi? the Wind`2`28.00";
	private final static String CSV_BODY_3 = "Terry Jones`11/02/16`2 Baker St`Portland`OR`Death of a Salesman`1`15.00";

}

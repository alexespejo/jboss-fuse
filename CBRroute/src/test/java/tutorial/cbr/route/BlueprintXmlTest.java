package tutorial.cbr.route;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;
import org.apache.commons.oi.FileUtils;
import java.io.File;

public class BlueprintXmlTest extends CamelBlueprintTestSupport {

	// TODO Create test message bodies that work for the route(s) being tested
	// Expected message bodies
	protected String body1;
	protected String body2;
	protected String body3;
	protected String body4;
	protected String body5;
	protected String body6;
	// Templates to send to input endpoints
	@Produce(uri = "file:src/data?noop=true")
	protected ProducerTemplate inputEndpoint;
	@Produce(uri = "direct:OrderFulfillment")
	protected ProducerTemplate input2Endpoint;
	// Mock endpoints used to consume messages from the output endpoints and then perform assertions
	@EndpointInject(uri = "mock:output")
	protected MockEndpoint outputEndpoint;
	@EndpointInject(uri = "mock:output2")
	protected MockEndpoint output2Endpoint;
	@EndpointInject(uri = "mock:output3")
	protected MockEndpoint output3Endpoint;
	@EndpointInject(uri = "mock:output4")
	protected MockEndpoint output4Endpoint;
	@EndpointInject(uri = "mock:output5")
	protected MockEndpoint output5Endpoint;
	@EndpointInject(uri = "mock:output6")
	protected MockEndpoint output6Endpoint;

	@Test
	public void testCamelRoute() throws Exception {
		// Create routes from the output endpoints to our mock endpoints so we can assert expectations
		context.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				body1 = FileUtils.readFileToString(new File("src/data/message1.xml"), "UTF-8");
				body3 = FileUtils.readFileToString(new File("src/data/message3.xml"), "UTF-8");
				body5 = FileUtils.readFileToString(new File("src/data/message5.xml"), "UTF-8");
				body6 = FileUtils.readFileToString(new File("src/data/message6.xml"), "UTF-8");
				
				body2 = FileUtils.readFileToString(new File("src/data/message2.xml"), "UTF-8");
				body4 = FileUtils.readFileToString(new File("src/data/message4.xml"), "UTF-8");
			}
		});

		// Define some expectations

		// TODO Ensure expectations make sense for the route(s) we're testing
		outputEndpoint.expectedBodiesReceivedInAnyOrder(expectedBodies);

		// Send some messages to input endpoints
		for (Object expectedBody : expectedBodies) {
			inputEndpoint.sendBody(expectedBody);
		}

		// Validate our expectations
		assertMockEndpointsSatisfied();
	}

	@Override
	protected String getBlueprintDescriptor() {
		return "OSGI-INF/blueprint/blueprint.xml";
	}

}

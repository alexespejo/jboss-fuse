package com.redhat.training.jb421;

import java.util.concurrent.TimeUnit;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

public class RouteRecipientListTest extends CamelBlueprintTestSupport{
	
	@Override
	protected String getBlueprintDescriptor() {
		return "/OSGI-INF/blueprint/blueprint.xml";
	}
	
	private final static String CSV_ORDER_1 = "John Doe,11/10/16,123 Easy St,Anytown,AS,Batman,1,14.00";

	private final static String CSV_ORDER_2 = "John Doe,11/10/16,123 Easy St,Anytown,AS,Catcher in the Rye,2,14.00";

	private final static String CSV_ORDER_3 = "John Doe,11/10/16,123 Easy St,Anytown,AS,Batman,3,14.00";

	private final static int TIMEOUT = 2;
	
	@Produce(uri = "file://originOrders")
	private ProducerTemplate ordersProducerTemplate;

	// TODO Inject all mockendpoints
	@EndpointInject(uri = "mock:jms:comics")
	private MockEndpoint amqEndpoint;

	@EndpointInject(uri = "mock:ftp:infrastructure")
	private MockEndpoint ftpEndpoint;

	@EndpointInject(uri = "mock:file:test")
	private MockEndpoint testEndpoint;
	
	@Test
	public void testRoutes() throws Exception{
		NotifyBuilder builder = new NotifyBuilder(context).whenDone(1).create();
		amqEndpoint.expectedMessageCount(1);
		ftpEndpoint.expectedMessageCount(1);
		testEndpoint.expectedMessageCount(1);
		
		ordersProducerTemplate.sendBody(CSV_ORDER_1);
		ordersProducerTemplate.sendBody(CSV_ORDER_2);
		ordersProducerTemplate.sendBody(CSV_ORDER_3);
		
		builder.matches(TIMEOUT,TimeUnit.SECONDS);
		
		amqEndpoint.assertIsSatisfied();
		ftpEndpoint.assertIsSatisfied();
		testEndpoint.assertIsSatisfied();
		
	}
}

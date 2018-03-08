package com.redhat.training.jb421;

import java.util.concurrent.TimeUnit;

import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

import com.redhat.training.jb421.model.Order;
import com.redhat.training.jb421.predicates.Predicates;

public class RouteTest extends CamelBlueprintTestSupport {
	@Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/blueprint.xml";
    }
	
	@EndpointInject(uri = "mock:secondRoute")
	protected MockEndpoint resultHeaderMockEndpoint;
	
	@Produce(uri="direct:orderHeader")
	protected ProducerTemplate ordersHeaderProducerTemplate;
	
	@Test
	public void testRouteHeader() throws Exception {
		// Evaluate if the second route does not process a null body and header
		// named test with a null string
		//from builder
		resultHeaderMockEndpoint.setExpectedMessageCount(0);
		NotifyBuilder builder = new NotifyBuilder(context).fromRoute("orderHeader").whenDone(1).create();
		ordersHeaderProducerTemplate.sendBodyAndHeader(null, "test", null);
		builder.matches(2, TimeUnit.SECONDS);
		resultHeaderMockEndpoint.assertIsSatisfied();
		
		// from dsl
//		getMockEndpoint("mock:secondRoute").setMinimumExpectedMessageCount(0);
//		Endpoint endpoint = context.getEndpoint("direct:orderHeader");
//		template.setDefaultEndpoint(endpoint);
//		template.sendBodyAndHeader(null, "test", "input");
//		assertMockEndpointsSatisfied(2, TimeUnit.SECONDS);
	}

	@Test
	public void testRouteHeaderWithHeader() throws Exception {
		resultHeaderMockEndpoint.setExpectedMessageCount(0);
		NotifyBuilder builder = new NotifyBuilder(context).from("orderHeader").whenDone(1).create();
		ordersHeaderProducerTemplate.sendBodyAndHeader(null, "test", "input");
		builder.matches(2, TimeUnit.SECONDS);
		resultHeaderMockEndpoint.assertIsSatisfied();
	}

	@Test
	public void testRouteHeaderWithHeaderAndBody() throws Exception {
		resultHeaderMockEndpoint.setExpectedMessageCount(0);
		NotifyBuilder builder = new NotifyBuilder(context).from("orderHeader").whenDone(1).create();
		ordersHeaderProducerTemplate.sendBodyAndHeader(new Order(), "test", "input");
		builder.matches(2,TimeUnit.SECONDS);
		resultHeaderMockEndpoint.assertIsNotSatisfied();
	}
}
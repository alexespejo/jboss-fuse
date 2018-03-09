package com.redhat.training.jb421;

import java.util.concurrent.TimeUnit;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

public class RouteWithSemiColonTest extends CamelBlueprintTestSupport{
	

	@Override
	protected String getBlueprintDescriptor() {
		return "/OSGI-INF/blueprint/blueprint.xml";
	}

	@Produce(uri = "file:originSelectMethod")
	protected ProducerTemplate orderSelect;

	@Produce(uri = "file:origin")
	protected ProducerTemplate orders;

	@EndpointInject(uri = "mock:destinationSelectMethod")
	private MockEndpoint destinationSelect;

	@EndpointInject(uri = "mock:destination")
	private MockEndpoint destination;

	@Test
	public void testRouteFileWithSemiColon() throws Exception {
		NotifyBuilder builder = new NotifyBuilder(context).whenDone(1).create();
		destination.expectedMessageCount(1);
		destination.expectedBodiesReceived(CSV_ORDER_1_SEMICOLON);
		orders.sendBodyAndHeader(CSV_ORDER_1, "CamelFileName", "orders-v2-A1.csv");
		builder.matches(5,TimeUnit.SECONDS);
		destination.assertIsSatisfied();
		
//		Endpoint endpoint = context.getEndpoint("file:origin");
//		template.setDefaultEndpoint(endpoint);
//		
//		destination.expectedMessageCount(1);
//		destination.expectedBodiesReceived(CSV_ORDER_1_SEMICOLON);
//		
//		template.sendBodyAndHeader(CSV_ORDER_1,"CamelFileName","orders-v2-A1.csv");
//		assertMockEndpointsSatisfied(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void testRouteFileWithColon() throws Exception {
		NotifyBuilder builder = new NotifyBuilder(context).whenDone(1).create();
		destinationSelect.expectedMessageCount(1);
		destinationSelect.expectedBodiesReceived(CSV_ORDER_2_COLON);
		orderSelect.sendBodyAndHeader(CSV_ORDER_2,"CamelFileName","orders-v2-A2.csv");
		builder.matches(5,TimeUnit.SECONDS);
		destinationSelect.assertIsSatisfied();
	}

	private final static String CSV_ORDER_1 = "John Doe,11/10/16,123 Easy St,Anytown,AS,Catcher in the Rye,1,14.00\n"
			+ "Amy Smith,10/31/16,2 Knightdown Ave,Carlsbad,CA,Gone with the Wind,2,28.00\n"
			+ "Terry Jones,11/02/16,2 Baker St,Portland,OR,Death of a Salesman,1,15.00";
	private final static String CSV_ORDER_1_SEMICOLON = "John Doe;11/10/16;123 Easy St;Anytown;AS;Catcher in the Rye;1;14.00\n"
			+ "Amy Smith;10/31/16;2 Knightdown Ave;Carlsbad;CA;Gone with the Wind;2;28.00\n"
			+ "Terry Jones;11/02/16;2 Baker St;Portland;OR;Death of a Salesman;1;15.00";

	private final static String CSV_ORDER_2 = "John Doe,11/10/16,123 Easy St,Anytown,AS,Catcher in the Rye,1,14.00\n"
			+ "Amy Smith,10/31/16,2 Knightdown Ave,Carlsbad,CA,Gone with the Wind,2,28.00\n"
			+ "Terry Jones,11/02/16,2 Baker St,Portland,OR,Death of a Salesman,1,15.00";
	private final static String CSV_ORDER_2_COLON = "John Doe:11/10/16:123 Easy St:Anytown:AS:Catcher in the Rye:1:14.00\n"
			+ "Amy Smith:10/31/16:2 Knightdown Ave:Carlsbad:CA:Gone with the Wind:2:28.00\n"
			+ "Terry Jones:11/02/16:2 Baker St:Portland:OR:Death of a Salesman:1:15.00";
}

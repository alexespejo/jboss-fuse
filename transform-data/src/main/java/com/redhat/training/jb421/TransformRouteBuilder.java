package com.redhat.training.jb421;

import org.apache.camel.builder.RouteBuilder;

public class TransformRouteBuilder extends RouteBuilder {
	
	public static String OUTPUT_FOLDER = "/home/ihsan/Documents/jboss-fuse/transform-data/orders/outgoing";

	public static Long BATCH_TIMEOUT = 10000L;
	
	
	@Override
	public void configure() throws Exception {
		//TODO add jpa consumer
		from("jpa:com.redhat.training.jb421.model.Order?persistenceUnit=oracle"
				+ "&consumeDelete=false"
				+ "&consumer.namedQuery=getUndeliveredOrders"
				+ "&maximumResults=1"
				+ "&consumer.delay=30000"
				+ "&consumeLockEntity=false")
		.log("${body}")
		
		//TODO add wire tap to second route
		.wireTap("direct:updateOrder");
		
		//TODO marshal order to XML with JAXB
		
		//TODO split the order into individual order items
		
		//TODO aggregate the order items based on their catalog item id
		
		//TODO log the reservation XML to the console
		
		//TODO add file producer
		
		
		//TODO add second route to update order in the database
		from("direct:updateOrder")
		.process(new DeliverOrderProcessor())
		.to("jpa:com.redhat.training.jb421.model.Order?persistenceUnit=oracle");
		
	}

}

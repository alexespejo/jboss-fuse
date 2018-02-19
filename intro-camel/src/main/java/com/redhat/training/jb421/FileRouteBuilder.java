package com.redhat.training.jb421;

import org.apache.camel.builder.RouteBuilder;

public class FileRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:orders/incoming")
		  .routeId("OrderRoute1")
		  .setHeader("amount", xpath("/order/orderItems/orderItem/orderItemPrice/text()"))
		  .log("Incoming amount is ${header.amount}")
		  .filter(xpath("/order/orderItems/orderItem/orderItemPrice/text()>110"))
		  .log("Accepted order from file: ${header.CamelFileNameOnly}")
		  .to("file:orders?fileName=journal.xml&fileExist=Append");
	}

}

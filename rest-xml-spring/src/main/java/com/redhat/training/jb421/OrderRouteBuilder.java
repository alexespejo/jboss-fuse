package com.redhat.training.jb421;

import org.apache.camel.builder.RouteBuilder;

public class OrderRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// use CXF-RS to setup the REST web service using the resource class and
		// the simple binding style
		from("cxfrs:http://localhost:8383?resourceClasses=com.redhat.training.jb421.RestOrderService&"
				+ "bindingStyle=SimpleConsumer")
				// call the route based on the operation invoked on the REST web service
				.toD("direct:${header.operationName}");
		
		// routes that implements the REST Services
		from("direct:createOrder")
			.log("Creating new order with id ${header.id}")
			.setHeader("orderDate").groovy("new Date()")
			.to("sql:insert into order_ (id, orderDate) values (:#id, :#orderDate)?dataSource=oracleDataSource");

		from("direct:updateOrder")
			.log("Updating order with id ${header.id} to add order item ${body}")
			.bean(new XPathOrderItemProcessor())
			.to("sql:insert into OrderItem (id, order_id, quantity, extPrice) values "
					+ "(:#id, :#orderId, :#quantity, :#extPrice)?dataSource=oracleDataSource");
		
		from("direct:cancelOrder")
			.log("Canceling order with id ${header.id}")
			.to("sql:delete from OrderItem where order_id = (#)?dataSource=oracleDataSource")
			.to("sql:delete from order_ where id = (#)?dataSource=oracleDataSource");
	}

}

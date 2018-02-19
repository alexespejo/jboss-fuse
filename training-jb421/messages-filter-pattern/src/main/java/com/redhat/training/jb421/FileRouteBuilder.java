package com.redhat.training.jb421;

import org.apache.camel.builder.RouteBuilder;

public class FileRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("file://origin")
				//.filter(xpath("/order/orderItems/orderItem/orderItemPublisherName/text()='ABC Company'"))
				.filter(xpath("/order/shippingAddress[not(contains(zipCode,'23221'))]"))
				//.filter(xpath("/order/orderItems/orderItem[not(contains(orderItemPublisherName,'ABC Company'"))
				.to("file://destination?fileExist=Append&Fileame=output.xml");
	}
}

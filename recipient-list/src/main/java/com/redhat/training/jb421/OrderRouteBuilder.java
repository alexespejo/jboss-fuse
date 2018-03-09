package com.redhat.training.jb421;

import org.apache.camel.builder.RouteBuilder;

import com.redhat.training.jb421.beans.BodyTransformBean;

public class OrderRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("file:origin")
		.bean(BodyTransformBean.class,"replaceCommaWithSemiColon")
		.to("mock:destination");
		
		from("file:originSelectMethod")
		.to("bean:bodyTransformBean?method=replaceCommaWithColon")
		.to("mock:destinationSelectMethod");
		
		from("file:originOrders")
		.bean("destinationBean")
		.recipientList(header("destination"));
	}

}

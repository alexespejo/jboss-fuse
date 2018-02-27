package com.redhat.training.jb421;

import org.apache.camel.builder.RouteBuilder;

public class TransformRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:orderLog").split(body()).log("${body}").to("mock:orderLoggingSystem");
	}
}

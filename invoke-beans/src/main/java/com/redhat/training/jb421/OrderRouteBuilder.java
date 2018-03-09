package com.redhat.training.jb421;

import org.apache.camel.builder.RouteBuilder;

import com.redhat.training.jb421.beans.BodyTransformBean;
import com.redhat.training.jb421.beans.DestinationBean;

public class OrderRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("file:origin")
			.routeId("transformComma")
			.bean(BodyTransformBean.class, "replaceCommaWithBacktick")
			.to("direct:transformChars");

		from("direct:transformChars")
			.routeId("transformChars")
			//.to("bean://bodyTransformBean?method=replaceNonASCIIWithQuestionMark")
			.bean(BodyTransformBean.class,"replaceNonASCIIWithQuestionMark")
			.to("direct:recipientList");

		from("direct:recipientList").routeId("recipientList").split(body().convertToString().tokenize("\n"))
				.setHeader("destination", method(DestinationBean.class, "calculateDestination"))
				// .bean(DestinationBean.class,"processDestination")
				.recipientList(header("destination"));
	}

}

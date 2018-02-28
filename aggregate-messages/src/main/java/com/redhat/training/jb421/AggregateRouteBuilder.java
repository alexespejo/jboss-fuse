package com.redhat.training.jb421;

import org.apache.camel.builder.RouteBuilder;

public class AggregateRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("activemq:queue:orderInput?username=admin&password=admin").marshal().jaxb()
				// add aggregate by header("orderType") here using
				// OrderBatchAggregationStrategy()
				.aggregate(header("orderType"), new OrderBatchAggregationStrategy()).completionInterval(5000)

				// add xslt using provided style sheet for updating
				.to("xslt:CurrencySymbol.xsl")
				// extPrice and price to include the $ symbol

				.log("${body}").to("mock:fulfillmentSystem");
	}

}

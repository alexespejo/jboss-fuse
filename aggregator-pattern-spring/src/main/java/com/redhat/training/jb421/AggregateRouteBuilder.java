package com.redhat.training.jb421;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;

import com.redhat.training.jb421.model.Order;

public class AggregateRouteBuilder extends RouteBuilder {

	public static String SRC_FOLDER = "/home/ihsan/Documents/jboss/aggregator-pattern-spring/orders/incoming";
	public static String OUTPUT_FOLDER = "/home/ihsan/Documents/jboss/aggregator-pattern-spring/orders/outgoing";

	BindyFixedLengthDataFormat bindy = new BindyFixedLengthDataFormat(Order.class);

	@Override
	public void configure() throws Exception {
		from("file://" + SRC_FOLDER).split()
		.tokenize("\n")
		.streaming()
		.unmarshal(bindy)
		.aggregate(constant(true), new ArrayListAggregationStrategy())
		.completionSize(25)
		.completeAllOnStop()
		.process(new BatchXMLProcessor())
		.wireTap("direct:orderLogger")
		.to("file://" + OUTPUT_FOLDER + "?fileName=output.xml&fileExist=Append", "mock:result");

		from("direct:orderLogger")
		.split()
		.tokenizeXML("order")
		.log("${body}");
	}
}

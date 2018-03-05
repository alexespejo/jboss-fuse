package com.redhat.training.jb421;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.common.HttpOperationFailedException;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.dataformat.xmljson.XmlJsonDataFormat;

public class EnrichRouteBuilder extends RouteBuilder {

	public static String OUTPUT_FOLDER = "/home/ihsan/Documents/jboss-fuse/enrich-message/orders/outgoing";
	public static String ERROR_FOLDER = "/home/ihsan/Documents/jboss-fuse/enrich-message/orders/error";

	@Override
	public void configure() throws Exception {
		XmlJsonDataFormat xmlJsonDataFormat = new XmlJsonDataFormat();
		xmlJsonDataFormat.setRootName("order");
		xmlJsonDataFormat.setElementName("orderItem");

		JaxbDataFormat jaxbDataFormat = new JaxbDataFormat("com.redhat.training.jb421.model");

		onException(HttpOperationFailedException.class).handled(true);

		// from("timer:orderTimer?period=30s")
		// .to("http://localhost:8080/bookstore/rest/order/fulfillOrder")
		from("file:json")
		.log("datanya : ${body}")
		.unmarshal(xmlJsonDataFormat)
		.log("${body}")
		.to("file:///home/ihsan/Documents/jboss-fuse/enrich-message/orders/xml?fileName=output.xml")
		.unmarshal(jaxbDataFormat)
		.enrich("direct:vendorLookupJDBC", new VendorLookupAggregationStrategy());
//		.to("file://" + OUTPUT_FOLDER + "?fileName=output${date:now:yyyy_MM_dd_hh_mm_ss}.xml");
		 
		from("direct:vendorLookupJDBC")
			.doTry()
			.process(new VendorLookupProcessor()).to("jdbc://dataSource")
			.doCatch(Exception.class)
			.log("Exception in database lookup! ${body}")
			.to("file://" + ERROR_FOLDER + "?fileName=output${date:now:yyyy_MM_dd_hh_mm_ss}.xml").end();
	}
}

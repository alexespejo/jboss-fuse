package com.redhat.training.jb421;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.dataformat.xmljson.XmlJsonDataFormat;
import org.apache.camel.http.common.HttpOperationFailedException;

public class EnrichRouteBuilder extends RouteBuilder {

	public static String OUTPUT_FOLDER = "/home/ihsan/Documents/jboss-fuse/enrich-message-spring/orders/outgoing";
	public static String ERROR_FOLDER = "/home/ihsan/Documents/jboss-fuse/enrich-message-spring/orders/error";
	
	@Override
	public void configure() throws Exception {
		
		XmlJsonDataFormat xmlJsonDataFormat = new XmlJsonDataFormat();
		xmlJsonDataFormat.setRootName("order");
		xmlJsonDataFormat.setElementName("orderItem");
		
		JaxbDataFormat jaxbDataFormat = new JaxbDataFormat("com.redhat.training.jb421.model");
		
		onException(HttpOperationFailedException.class)
		.handled(true);
		
		//TODO add timer component to pool every 30 seconds
		//from("direct:producer")
		//from("timer:orderTimer?period=30s")
		//TODO add http call to get JSON order data
		//.to("http://localhost:8080/bookstore/rest/order/fulfillOrder")
		//TODO convert data JSON to XML
		from("file:json")
		.unmarshal(xmlJsonDataFormat)
		.log("${body}")
		.unmarshal(jaxbDataFormat)
		//TODO add enrich to direct vendor lookup
		.enrich("direct:vendorLookupJDBC",new VendorLookupAggregationStrategy())
		.log("The body after aggregation: ${body}")
		.to("file://"+OUTPUT_FOLDER+"?fileName=output${date:now:yyyy_MM_dd_hh_mm_ss}.xml");
		
		//TODO add direct vendor lookup jdbc route
		from("direct:vendorLookupJDBC")
		.doTry()
		.log("Success in doTry()")
		.process(new VendorLookupProcessor())
		.log("After success with LookupProcessor() with ${body}")
		.to("jdbc:dataSource")
		.doCatch(Exception.class)
		.log("Exception in database lookup! ${body}")
		.to("file://"+ERROR_FOLDER+"?fileName="
				+ "output${date:now:yyyy_MM_dd_hh_mm_ss}.xml")
		.end()
		;
	}
	
}

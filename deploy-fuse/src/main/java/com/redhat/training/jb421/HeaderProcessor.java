package com.redhat.training.jb421;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.xml.XPathBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeaderProcessor implements Processor {
	
	final private static Logger log = LoggerFactory.getLogger(HeaderProcessor.class);
	
	final private static String JOURNAL_URI = "file:orders?fileExist=Append&fileName=journal";
	final private static String QUEUE_URI = "activemq:queue:abc?username={{amq.username}}&password={{amq.password}}";
	
	final private static String XPATH_VENDOR = "/order/orderItems/orderItem/orderItemPublisherName/text()";

	@Override
	public void process(Exchange exchange) throws Exception {
		log.info("Generating destinationURI header..");
		String orderXml = exchange.getIn().getBody(String.class);
		String vendorName = XPathBuilder.xpath(XPATH_VENDOR).evaluate(exchange.getContext(), orderXml);
		log.info("Vendor: "+vendorName);
		
		if("ABC Company".equals(vendorName)){
			exchange.getIn().setHeader("destinationURI", QUEUE_URI);
		}else{
			exchange.getIn().setHeader("destinationURI", JOURNAL_URI+vendorName+".txt");
		}
	}

}

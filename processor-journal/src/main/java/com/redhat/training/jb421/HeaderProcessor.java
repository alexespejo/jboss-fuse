package com.redhat.training.jb421;


import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.camel.builder.xml.XPathBuilder;

public class HeaderProcessor implements Processor{
final private static Logger log = LoggerFactory.getLogger(HeaderProcessor.class);
	
	final private static String JOURNAL_URI = "file:orders?fileExist=Append&fileName=journal";
	final private static String XPATH_VENDOR = "/order/orderItems/orderItem/orderItemPublisherName/text()";
	final private static String XPATH_DATE = "2016-10-11";

	@Override
	public void process(Exchange exchange) throws Exception {
		log.info("Generating the journalFileName header...");
	    String orderXml = exchange.getIn().getBody(String.class);
	    String orderDateTime = 
	        XPathBuilder.xpath(XPATH_DATE).evaluate(exchange.getContext(),
	        orderXml);
	    log.info("orderDateTime: " + orderDateTime);
	    Calendar orderCal = DatatypeConverter.parseDateTime(orderDateTime);
	    String orderDate = df.format (orderCal.getTime());
	    exchange.getIn().setHeader("journalFileName", "journal-" + orderDate + ".txt");
	}

}

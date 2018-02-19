package com.redhat.training.jb421;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.util.MessageDump.Header;

public class ExchangePrinter implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Body body = exchange.getIn().getBody(String.class);
		Header headers = exchange.getIn().getHeaders();
	}

}

package org.box.fuse.library;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BookProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		// Get input from exchange
		Object inputObj = exchange.getIn().getBody();

		// convert to JSON
		ObjectMapper mapper = new ObjectMapper();
		String outputJson = mapper.writeValueAsString(inputObj);

		// set output in exchange
		exchange.getOut().setBody(outputJson);
	}

}

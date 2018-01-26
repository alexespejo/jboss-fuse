package org.box.fuse.library;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.message.MessageContentsList;

public class BorrowProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		// get input from exchange
		Object[] input = exchange.getIn().getBody(MessageContentsList.class).toArray();

		Map<String, String> map = new HashMap<String, String>();
		map.put("username", input[0].toString());
		map.put("bookid", input[1].toString());

		exchange.getIn().setBody(map);
	}
}

package com.redhat.training.jb421;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.jb421.model.Order;
import com.redhat.training.jb421.model.OrderItem;

public class VendorLookupProcessor implements Processor {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void process(Exchange exchange) throws Exception {

		Message existing = exchange.getIn();

		Order incomingOrder = existing.getBody(Order.class);

		// build a SQL query
		StringBuilder query = new StringBuilder("select sku, pub_id, id " + "from CatalogItem where pub_id in (");

		for (OrderItem order : incomingOrder.getOrderItems()) {
			query.append(order.getCatalogItem().getId());
			query.append(",");
		}

		query.delete(query.lastIndexOf(","), query.length());
		query.append(")");

		log.info("Query: " + query.toString());
		// append the output to the body
		existing.setBody(query.toString());
	}

}

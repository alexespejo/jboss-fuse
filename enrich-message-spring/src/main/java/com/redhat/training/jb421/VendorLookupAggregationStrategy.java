package com.redhat.training.jb421;

import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigDecimal;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.redhat.training.jb421.model.Order;
import com.redhat.training.jb421.model.OrderItem;

public class VendorLookupAggregationStrategy implements AggregationStrategy {

	@SuppressWarnings("unchecked")
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange databaseResult) {

		Order originalBody = oldExchange.getIn().getBody(Order.class);
		ArrayList<HashMap<String, Object>> dbResult = databaseResult.getIn().getBody(ArrayList.class);

		// match the results from the database	
		for (OrderItem item : originalBody.getOrderItems()) {

			for (HashMap<String, Object> row : dbResult) {
				//Integer grandChildCount = ((BigInteger) result[1]).intValue();
				
				int rowId = ((BigDecimal) row.get("ID")).intValue();
				if (rowId == item.getCatalogItem().getId()) {
					item.setVendorId((Integer) row.get("PUB_ID"));
					item.setSku((String) row.get("SKU"));
				}
			}
		}

		return oldExchange;
	}
}

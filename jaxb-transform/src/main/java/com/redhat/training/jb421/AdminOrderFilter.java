package com.redhat.training.jb421;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.camel.Exchange;
import org.apache.camel.model.language.ExpressionDefinition;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.training.jb421.model.Order;

public class AdminOrderFilter extends ExpressionDefinition {
	
	@Override
	public boolean matches(Exchange exchange) {
		Order order = exchange.getIn().getBody(Order.class);
		//filter out any orders where the customer is an admin
		if(order != null && order.getCustomer() != null && order.getCustomer().isAdmin()){
			log.info("Filtering out admin order!");
			return false;
		}
		return true;
	}
	
	private String getExpectedJSONString(Order order)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		// Buffer to hold JSON string
		StringWriter sw = new StringWriter();
		objectMapper.writeValue(sw, order);
		return sw.toString();
	}
}

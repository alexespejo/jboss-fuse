package com.redhat.training.jb421;

import org.apache.camel.Exchange;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

import com.redhat.training.jb421.model.Order;
import com.redhat.training.jb421.predicates.Predicates;

public class PredicatesTest extends CamelBlueprintTestSupport {
	@Override
	protected String getBlueprintDescriptor() {
		return "/OSGI-INF/blueprint/blueprint.xml";
	}

	@Test
	public void testNullHeaderPredicates() {
		Exchange exchange = ExchangeBuilder.anExchange(context).build();
		Order order = null;
		exchange.getIn().setBody(order);
		exchange.getIn().setHeader("test", null);
		assertPredicateDoesNotMatch(Predicates.bodyAndHeaderNotNull(), exchange);
	}
}
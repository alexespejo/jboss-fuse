package com.redhat.training.jb421;


import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.ExchangeBuilder;

import com.redhat.training.jb421.model.Order;
import com.redhat.training.jb421.predicates.Predicates;

public class PredicatesTest extends CamelSpringTestSupport{

	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
	}
	
	@Test
	public void testNullHeaderPredicates(){
		Exchange exchange = ExchangeBuilder.anExchange(context).build();
		Order order = null;
		exchange.getIn().setBody(order);
		exchange.getIn().setHeader("test", null);
		assertPredicateDoesNotMatch(Predicates.bodyAndHeaderNotNull(), exchange);
	}
}

package com.redhat.training.jb421;

import org.apache.camel.Exchange;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;

import org.junit.Test;

import com.redhat.training.jb421.model.Address;
import com.redhat.training.jb421.model.Customer;
import com.redhat.training.jb421.model.Order;
import com.redhat.training.jb421.model.OrderItem;
import com.redhat.training.jb421.predicates.OrderPredicates;

public class PredicatesTest extends CamelTestSupport {

	@Test
	public void testValidZipCodePredicates() {
		Exchange validexchange = ExchangeBuilder.anExchange(context).build();
		Order order = createNewOrder("25411-2211");
		validexchange.getIn().setBody(order);
		assertPredicateMatches(OrderPredicates.validateZipCode(), validexchange);
		// Assert.fail("Not implemented yet");
	}

	@Test
	public void testInvalidZipCodePredicates() {
		Exchange invalidExchange = ExchangeBuilder.anExchange(context).build();
		Order invalidOrder = createNewOrder("X7XXX-XX4X");
		invalidExchange.getIn().setBody(invalidOrder);
		assertPredicateDoesNotMatch(OrderPredicates.validateZipCode(), invalidExchange);
	}

	@Test
	public void testValidNumberOfItemsPredicates() {
		Exchange validExchange = ExchangeBuilder.anExchange(context).build();
		Order order = createNewOrder(new OrderItem(), new OrderItem(), new OrderItem());
		validExchange.getIn().setBody(order);
		assertPredicateMatches(OrderPredicates.validateNumberOfItems(), validExchange);
	}

	@Test
	public void testInvalidNumberOfItemsPredicate() {
		Exchange invalidExchange = ExchangeBuilder.anExchange(context).build();
		Order invalidOrder = createNewOrder(new OrderItem());
		invalidExchange.getIn().setBody(invalidOrder);
		assertPredicateDoesNotMatch(OrderPredicates.validateNumberOfItems(), invalidExchange);
	}

	private Order createNewOrder(String zipCode) {
		Order order = new Order();
		Customer newCustomer = new Customer();
		Address newAddress = new Address();
		newAddress.setPostalCode(zipCode);
		newCustomer.setShippingAddress(newAddress);
		order.setCustomer(newCustomer);
		return order;
	}

	private Order createNewOrder(OrderItem... items) {
		Order order = new Order();
		for (OrderItem orderItem : items) {
			order.getOrderItems().add(orderItem);
		}
		return order;
	}

}

package com.redhat.training.jb421;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.component.mock.MockEndpoint;

import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.training.jb421.model.Address;
import com.redhat.training.jb421.model.CatalogItem;
import com.redhat.training.jb421.model.Customer;
import com.redhat.training.jb421.model.Order;
import com.redhat.training.jb421.model.OrderItem;

@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/camel-context.xml" })
public class TransformRouteTest {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@EndpointInject(uri = "mock:fulfillmentSystem")
	protected MockEndpoint fullfillmentEndpoint;

	@Produce(uri = "direct:orderInput")
	protected ProducerTemplate orderProcedur;

	@Autowired
	protected CamelContext context;

	@EndpointInject(uri = "mock:orderLog")
	protected MockEndpoint orderLogEndpoint;

	@Test
	@DirtiesContext
	public void testDroppingOrder() {
		try {
			NotifyBuilder builder = new NotifyBuilder(context).whenDone(1).create();
			builder.matches(2000, TimeUnit.MILLISECONDS);

			Order testNonAdminOrder = createTestOrder(false);

			String nonAdminXML = getExpectedXmlString(testNonAdminOrder);
			String nonAdminJSON = getExpectedJSONString(testNonAdminOrder);

			fullfillmentEndpoint.expectedBodiesReceived(nonAdminXML);
			orderLogEndpoint.expectedBodiesReceived(nonAdminJSON);

			orderProcedur.sendBody(testNonAdminOrder);

			fullfillmentEndpoint.assertIsSatisfied();
			orderLogEndpoint.assertIsSatisfied();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@DirtiesContext
	public void testDroppingAdminOrder() {
		try {
			NotifyBuilder builder = new NotifyBuilder(context).whenDone(1).create();
			builder.matches(2000, TimeUnit.MILLISECONDS);

			Order testAdminOrder = createTestOrder(true);

			fullfillmentEndpoint.setExpectedCount(0);

			orderProcedur.sendBody(testAdminOrder);

			fullfillmentEndpoint.assertIsSatisfied();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Order createTestOrder(boolean isAdmin) {
		Address testAddress = new Address();
		testAddress.setCity("Releigh");
		testAddress.setCountry("USA");
		testAddress.setPostalCode("27700");
		testAddress.setState("NC");
		testAddress.setStreetsAddress1("100 E, Davie street");

		Customer testCustomer = new Customer();
		testCustomer.setAdmin(isAdmin);
		testCustomer.setBillingAddres(testAddress);
		testCustomer.setShippingAddress(testAddress);
		testCustomer.setEmail("tester@redhat.com");
		testCustomer.setFirstname("ihsan");
		testCustomer.setLastname("arif");
		testCustomer.setUsername("ihsan");
		testCustomer.setPassword("1234567890");

		CatalogItem testCatalogItem = new CatalogItem();
		testCatalogItem.setAuthor("Ihsan Arif");
		testCatalogItem.setCategory("Fiction");
		testCatalogItem.setDescription("Description lorem ipsum");
		testCatalogItem.setImagePath("books/lifeofpi/cover.jpg");
		testCatalogItem.setNewItem(false);
		testCatalogItem.setPrice(new BigDecimal("15.99"));
		testCatalogItem.setSku("123456789");
		testCatalogItem.setTitle("Life of Pi");

		OrderItem testItem = new OrderItem();
		testItem.setExtPrice(new BigDecimal("5.99"));
		testItem.setItem(testCatalogItem);
		testItem.setQuantity(1);

		Order testOrder = new Order();
		testOrder.setDelivered(false);
		testOrder.setOrderDate(new Date());
		testOrder.setCustomer(testCustomer);

		return testOrder;
	}

	private String getExpectedXmlString(Order order) throws JAXBException {
		JAXBContext jaxbcontext = JAXBContext.newInstance(Order.class);
		StringWriter sw = new StringWriter();

		Marshaller marshaller = jaxbcontext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(order, sw);

		return sw.toString();
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

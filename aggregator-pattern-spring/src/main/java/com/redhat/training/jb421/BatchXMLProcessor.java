package com.redhat.training.jb421;

import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.redhat.training.jb421.model.Order;

public class BatchXMLProcessor implements Processor {

	@SuppressWarnings("unchecked")
	@Override
	public void process(Exchange exchange) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(Order.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		
		StringBuilder batchXML = new StringBuilder();
		
		batchXML.append("<batch>");
		
		List<Order> orderBatch = exchange.getIn().getBody(List.class);
		for(Order order : orderBatch){
			StringWriter sw = new StringWriter();
			marshaller.marshal(order, sw);
			String orderXML = sw.toString();
			sw.close();
			batchXML.append(orderXML);
		}
		
		batchXML.append("</batch>");
		
		exchange.getIn().setBody(batchXML.toString());
		
		
	}

}

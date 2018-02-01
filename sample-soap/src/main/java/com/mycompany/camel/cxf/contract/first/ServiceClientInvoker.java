package com.mycompany.camel.cxf.contract.first;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.example.xmlns._1386864564116.GetResult;

import data.inputmessage.REQ;
import data.outputmessage.RES;

public class ServiceClientInvoker implements Runnable, Processor, Serializable {

	private GetResult myService;

	public GetResult getMyService() {
		return myService;
	}

	public void setMyService(GetResult myService) {
		this.myService = myService;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		REQ requestData = new REQ();
		String inputString = exchange.getIn().getBody(java.lang.String.class);
		String[] parts = inputString.split("-");
		String part1 = parts[0]; // 004
		String part2 = parts[1]; // 034556

		requestData.setFIRSTNAME(part1);
		requestData.setLASTNAME(part2);

		RES responseObj = myService.getResultAndRank(requestData);

		JAXBContext jc = JAXBContext.newInstance(new Class[] { RES.class });

		Marshaller um = jc.createMarshaller();
		StringWriter sw = new StringWriter();

		um.marshal(responseObj, sw);

		InputStream is = new ByteArrayInputStream(sw.toString().getBytes());
		org.w3c.dom.Document xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);

		DOMSource domSource = new DOMSource(xmlDocument);
		StringWriter writer1 = new StringWriter();
		StreamResult result = new StreamResult(writer1);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();

		transformer.transform(domSource, result);

		exchange.getIn().setBody(writer1.toString());

	}

	private Thread t;

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public void init() {

		t = new Thread(this, "SAPPI web service invoker thread");
		t.start();

	}

	public void destroy() {
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
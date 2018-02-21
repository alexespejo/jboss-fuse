package com.redhat.training.jb421;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WrapProcessor implements Processor {

    final private static Logger log = LoggerFactory.getLogger(WrapProcessor.class);

    final private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z");

    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("Wrapping order...");
        String orderXml = exchange.getIn().getBody(String.class);
        exchange.getIn()
                .setBody("<journal>\n" + "<timestamp>" + df.format(new Date()) + "</timestamp>\n" + "<fileName>"
                        + exchange.getIn().getHeader("CamelFileName", String.class) + "</fileName>\n" + orderXml
                        + "</journal>\n");

    }

}

package com.redhat.training.jb421;

import org.apache.camel.builder.RouteBuilder;

public class FTPRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("ftp://server.ihsanarif.com?username=ihsan&password=Rahman13021092&delete=true&include=order.*xml")
				.log("New file ${header.CamelFileName} picked up from ${header.CamelFileHost}")
				.process(new ExchangePrinter())
				.to("file:orders?fileName=fileName=${header.CamelFileHost}&fileExist=Append");
	}

}

package com.redhat.training.jb421;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;

import com.redhat.training.jb421.model.CatalogItem;

public class TransformRouteBuilder extends RouteBuilder {

	public static String SRC_FOLDER = "/home/ihsan/Documents/jboss/bindy-mtp-spring/items/incoming";

	BindyCsvDataFormat bindy = new BindyCsvDataFormat(CatalogItem.class);

	@Override
	public void configure() throws Exception {
		from("file:" + SRC_FOLDER).transform(body().regexReplaceAll("\n", "\r\n")).log("${body}").unmarshal(bindy)
				.log("menuju logging system").wireTap("direct:loggingSystem").to("mock:inventorySystem");

		from("direct:loggingSystem").split().simple("${body}").convertBodyTo(String.class).log("${body}")
				.to("mock:newItemsFeed");
	}
}

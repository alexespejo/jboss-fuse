package com.redhat.training.jb421;

import org.apache.camel.builder.RouteBuilder;

public class FTPRouteBuilder extends RouteBuilder {
	private static String FTP_SERVER = "ftp://{{server.hostname}}:{{server.port}}";
	private static String FTP_OPTIONS = "?username={{server.username}}&password={{server.password}}&noop=true";
	//private static String DIRECT = "direct:header";
	private static String DIRECT = "direct:wrap";

	public static String SRC_FOLDER1 = "/Documents/jboss/processor-journal/orders/incoming";

	@Override
	public void configure() throws Exception {

		from(FTP_SERVER + SRC_FOLDER1 + FTP_OPTIONS).to(DIRECT)
				.log("Alamat server : " + FTP_SERVER + SRC_FOLDER1 + FTP_OPTIONS);
	}
}
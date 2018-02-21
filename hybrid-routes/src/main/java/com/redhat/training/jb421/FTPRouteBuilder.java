package com.redhat.training.jb421;

import org.apache.camel.builder.RouteBuilder;

public class FTPRouteBuilder extends RouteBuilder {
	private static String FTP_SERVER = "ftp://{{server.hostname}}:{{server.port}}";
	private static String FTP_OPTIONS = "?username={{server.username}}&password={{server.password}}";
	private static String DIRECT = "direct:header";
	// private static String DIRECT = "direct:wrap";
	
	public static String SRC_FOLDER1 = "/Documents/jboss/test_order/order2/abc";
	public static String SRC_FOLDER2 = "/Documents/jboss/test_order/order2/orly";
	public static String SRC_FOLDER3 = "/Documents/jboss/test_order/order2/namming";

	@Override
	public void configure() throws Exception {
		
		from(FTP_SERVER + SRC_FOLDER1 + FTP_OPTIONS).to(DIRECT).log("Alamat server : "+FTP_SERVER+SRC_FOLDER1+FTP_OPTIONS);
		from(FTP_SERVER + SRC_FOLDER2 + FTP_OPTIONS).to(DIRECT).log("Alamat server : "+FTP_SERVER+SRC_FOLDER2+FTP_OPTIONS);
		from(FTP_SERVER + SRC_FOLDER3 + FTP_OPTIONS).to(DIRECT).log("Alamat server : "+FTP_SERVER+SRC_FOLDER3+FTP_OPTIONS);
	}

}


//public class FTPRouteBuilder extends RouteBuilder {
//
//	private static String FTP_SERVER = "ftp://{{server.hostname}}:{{server.port}}";
//	private static String FTP_OPTION = "?username={{server.username}}&password={{server.password}}&noop=true";
//	private static String DIRECT = "direct:header";
////	private static String DIRECT = "direct:wrap";
//
//	public static String SRC_FOLDER1 = "/tempo";
//	public static String SRC_FOLDER2 = "/gualgaol";
//	public static String SRC_FOLDER3 = "/suppori";
//
//	@Override
//	public void configure() throws Exception {
//
//		from(FTP_SERVER + SRC_FOLDER1 + FTP_OPTION).to(DIRECT);
//		from(FTP_SERVER + SRC_FOLDER2 + FTP_OPTION).to(DIRECT);
//		from(FTP_SERVER + SRC_FOLDER3 + FTP_OPTION).to(DIRECT);
//	}
//
//}

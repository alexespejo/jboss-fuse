package com.redhat.training.jb421;

import org.apache.camel.builder.RouteBuilder;

public class FTPRouteBuilder extends RouteBuilder{
	private static String FTP_SERVER = "ftp:{{server.hostname}}:{{server.port}}";
	private static String FTP_OPTIONS = "?username={{server.username}}&password={{server.password}}";
	private static String DIRECT = "direct:header";
	// private static String DIRECT = "direct:wrap";
	
	public static String SRC_FOLDER1 = "/order/abc";
	public static String SRC_FOLDER2 = "/order/orly";
	public static String SRC_FOLDER3 = "/order/namming";

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from(FTP_SERVER+SRC_FOLDER1+FTP_OPTIONS)
		.to(DIRECT).log("Alamat server : "+FTP_SERVER+SRC_FOLDER1+FTP_OPTIONS);
		
		from(FTP_SERVER+SRC_FOLDER2+FTP_OPTIONS)
		.to(DIRECT).log("Alamat server : "+FTP_SERVER+SRC_FOLDER2+FTP_OPTIONS);
		
		from(FTP_SERVER+SRC_FOLDER3+FTP_OPTIONS)
		.to(DIRECT).log("Alamat server : "+FTP_SERVER+SRC_FOLDER3+FTP_OPTIONS);
	}

}

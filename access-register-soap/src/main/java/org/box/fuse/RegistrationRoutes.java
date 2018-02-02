package org.box.fuse;

import org.apache.camel.builder.RouteBuilder;

public class RegistrationRoutes {
	public void configure() throws Exception {
		// webservice responses
		OutputReportIncident ok = new OutputReportIncident();
		ok.setCode("OK");

		OutputReportIncident accepted = new OutputReportIncident();
		accepted.setCode("Accepted");

		from("cxf:bean:reportIncident").convertBodyTo(InputReportIncident.class)
				.wireTap("file://target/inbox/?fileName=request-${date:now:yyyy-MM-dd-HHmmssSSS}").choice()
				.when(simple("${body.givenName} == 'Claus'")).transform(constant(ok)).otherwise()
				.transform(constant(accepted));
	}
}

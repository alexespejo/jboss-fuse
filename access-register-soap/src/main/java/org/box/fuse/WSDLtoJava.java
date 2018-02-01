package org.box.fuse;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.tools.wsdlto.WSDLToJava;

public class WSDLtoJava {
	public static void main(String[] args) {
		String outputDestination = "src/data";
		generateWebServiceClient("http://localhost:40000/box/library/blueprint/register?wsdl", outputDestination);

	}

	public static void generateWebServiceClient(String wsdlUri, String outputDestination) {

		List<String> arguments = new ArrayList<String>();
		arguments.add("-autoNameResolution"); //$NON-NLS-1$
		arguments.add("-quiet"); //$NON-NLS-1$
		arguments.add("-client"); //$NON-NLS-1$

		arguments.add("-d"); //$NON-NLS-1$
		arguments.add(outputDestination);
		arguments.add(wsdlUri);

		String[] args = new String[arguments.size()];
		try {
			// TODO in case of issue: replace with a VMRunner (cf
			// generateWSDLFile)
			WSDLToJava.main(arguments.toArray(args));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

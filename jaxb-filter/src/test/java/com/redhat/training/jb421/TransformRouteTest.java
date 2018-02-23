package com.redhat.training.jb421;

import org.apache.camel.EndpointInject;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;

public class TransformRouteTest extends CamelBlueprintTestSupport{
	
	@EndpointInject(uri = "mock:fulfillmentSystem")
	protected MockEndpoint fulfillmentEndpoint;

	@EndpointInject(uri="mock:orderLog")
	protected MockEndpoint orderLogEndpoint;
}

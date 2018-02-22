package org.redhat.training.jb421;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransformRouteTest extends CamelBlueprintTestSupport{
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Override
    protected String getBlueprintDescriptor() {
           return "/OSGI-INF/blueprint/blueprint.xml";
    }
    
//	@EndpointInject(uri="mock:fulfillmentSystem")
//	protected MockEndpoint = fullfillmentEndpoint;
	
	@Produce(uri="direct:orderInput")
	protected ProducerTemplate orderProcedur;
	
	@Autowired
	protected 
}

package org.box.fuse.library;

import javax.annotation.Resource;
import javax.ws.rs.core.Context;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

//import com.wordnik.swagger.annotations.Api;
//import com.wordnik.swagger.annotations.ApiOperation;
//import com.wordnik.swagger.annotations.ApiParam;
//import com.wordnik.swagger.annotations.ApiResponses;
//import com.wordnik.swagger.annotations.ApiResponse;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Path("/member/")
//@Api(value="/member",description="member restFul")
public class MemberServiceProcessor {
	private static final Logger LOG = LoggerFactory.getLogger(MemberServiceProcessor.class);
	
	public MemberServiceProcessor() {
	}
}

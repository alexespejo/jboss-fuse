package org.box.fuse.service;

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

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponses;
import com.wordnik.swagger.annotations.ApiResponse;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.box.fuse.dao.MemberImpl;
import org.box.fuse.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Path("/memberservice/")
@Api(value = "/memberservice", description = "Operations about memberservice")
public class MemberService {

    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);
    private MessageContext jaxrsContext;
    private MemberImpl memberimpl;
    
    public MemberService() {
    	memberimpl = new MemberImpl();
    }
    
    @GET
    @Path("/members/all/")
    @Produces("application/json")
    public List<Member> allCustomer() {
    	LOG.info("Show all member");	
        return memberimpl.getAllMembers();
    }
    
    @GET
    @Path("/members/{username}/")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "Find Member by Username", notes = "More notes about this method", response = Member.class)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Invalid username supplied"),
            @ApiResponse(code = 204, message = "Customer not found")
            })
    public Member getMember(@ApiParam(value = "Username of Member to fetch", required = true) @PathParam("username") String username) {
        LOG.info("Invoking getMember, Member username is: {}", username);
        Member member = memberimpl.getMember(username);
        return member;
    }

    @PUT
    @Path("/customers/")
    @Consumes({ "application/xml", "application/json" })
    @Produces("application/json")
    @ApiOperation(value = "Update an existing Customer")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Invalid ID supplied"),
            @ApiResponse(code = 204, message = "Customer not found")
            })
    public Response updateMember(@ApiParam(value = "Member object that needs to be updated", required = true) Member member) {
        LOG.info("Invoking updateMember, Member name is: {}", member.getUsername());
        Member m = memberimpl.getMember(member.getUsername());
        Response r;
        if (m != null) {
        	memberimpl.update(member);
            r = Response.ok().build();
        } else {
            r = Response.notModified().build();
        }

        return r;
    }

    @POST
    @Path("/members/")
    @Consumes({ "application/xml", "application/json" })
    @ApiOperation(value = "Add a new Member")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Invalid Username supplied"), })
    public Response addMember(@ApiParam(value = "Member object that needs to be updated", required = true) Member member) {
        LOG.info("Invoking addMember, Member name is: {}", member.getFirstname());
        
        memberimpl.add(member);
        if (jaxrsContext.getHttpHeaders().getMediaType().getSubtype().equals("json")) {
            return Response.ok().type("application/json").entity(member).build();
        } else {
            return Response.ok().type("application/xml").entity(member).build();
        }
    }

    @DELETE
    @Path("/members/{username}/")
    @ApiOperation(value = "Delete Username")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Invalid Username supplied"),
            @ApiResponse(code = 204, message = "Customer not found")
            })
    public Response deleteMember(@ApiParam(value = "Username of Customer to delete", required = true) @PathParam("username") String username) {
        LOG.info("Invoking deleteMember, Member username is: {}", username);
        Member m = memberimpl.getMember(username);

        Response r;
        if (m != null) {
            r = Response.ok().build();
            memberimpl.delete(username);
        } else {
            r = Response.notModified().build();
        }

        return r;
    }

    @Context
    public void setMessageContext(MessageContext messageContext) {
        this.jaxrsContext = messageContext;
    }

}
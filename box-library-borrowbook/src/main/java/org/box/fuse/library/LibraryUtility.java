package org.box.fuse.library;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

public class LibraryUtility {
	public LibraryUtility() {
	}

	@GET
	@Path("/borrowbook/")
	@Produces(MediaType.APPLICATION_JSON)
	public void borrowBook(@QueryParam("username") String username, @QueryParam("bookid") int bookid) {
	}
}

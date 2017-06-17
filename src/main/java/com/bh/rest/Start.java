package com.bh.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by bharatviswanadham on 6/17/17.
 */
@Path("/")
public class Start {
    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String  start() {
        return "Start Page";
    }

}

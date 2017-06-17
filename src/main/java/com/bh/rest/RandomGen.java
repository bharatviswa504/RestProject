package com.bh.rest;

/**
 * Created by bharatviswanadham on 6/17/17.
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.Random;

@Path("/random")
public class RandomGen {

    @GET
    @Path("get")
    @Produces(MediaType.TEXT_PLAIN)
    public int test() {
        Random random = new Random();
        return random.nextInt();
    }
}

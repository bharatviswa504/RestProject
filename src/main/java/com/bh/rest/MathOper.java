package com.bh.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


/**
 * Created by bharatviswanadham on 6/17/17.
 */

@Path("/math")
public class MathOper {

    @GET
    @Path("square")
    @Produces(MediaType.APPLICATION_JSON)
    public Result square(@QueryParam("input") double input) {
        Result result = new Result("Square");
        result.setInput(input);
        result.setOutput(result.getInput() * result.getInput());
        return result;
    }

    @GET
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultSet add (@QueryParam("input1") double input1, @QueryParam("input2") double input2) {
        ResultSet rs = new ResultSet("add");
        rs.setInput1(input1);
        rs.setInput2(input2);
        rs.setResult(input1 + input2);
        return rs;

    }


}

package com.bh.rest;

/*
 * Created by bharatviswanadham on 6/17/17.
 */


public class Result {

    double input;
    double output;
    String action;

    /*
    MessageBodyWriter not found for media type=application/json type=class
    Default constructor is required. As jax-rs intialize classes
    https://stackoverflow.com/questions/26207252/messagebodywriter-not-found-for-media-type-application-json
   */

    public Result() {
    }

    public Result(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public double getInput() {
        return input;
    }


    public void setInput(double input) {
        this.input = input;
    }


    public double getOutput() {
        return output;
    }


    public void setOutput(double output) {
        this.output = output;
    }

}



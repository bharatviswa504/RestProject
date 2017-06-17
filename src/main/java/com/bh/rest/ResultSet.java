package com.bh.rest;



/**
 * Created by bharatviswanadham on 6/17/17.
 */
public class ResultSet {

    double input1;
    double input2;
    double result;
    String action;

    public ResultSet() {

    }

    public ResultSet(String action) {
        this.action = action;
    }


    public double getInput1() {
        return input1;
    }

    public void setInput1(double input1) {
        this.input1 = input1;
    }

    public double getInput2() {
        return input2;
    }

    public void setInput2(double input2) {
        this.input2 = input2;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}


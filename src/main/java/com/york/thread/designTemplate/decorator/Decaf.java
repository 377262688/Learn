package com.york.thread.designTemplate.decorator;

/**
 * @author york
 * @create 2020-03-27 09:44
 **/
public class Decaf extends Beverage {

    public Decaf() {
        description = "decaf";
    }
    @Override
    public double cost() {
        return 1.8;
    }
}

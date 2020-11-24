package com.york.thread.designTemplate.decorator;

/**
 * @author york
 * @create 2020-03-27 09:35
 **/
public abstract class Beverage {

    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}

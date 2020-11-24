package com.york.thread.designTemplate.decorator;

/**
 * @author york
 * @create 2020-03-27 09:42
 **/
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }
    @Override
    public double cost() {
        return 0.89;
    }
}

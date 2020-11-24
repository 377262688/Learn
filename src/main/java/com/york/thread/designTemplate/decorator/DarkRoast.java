package com.york.thread.designTemplate.decorator;

/**
 * @author york
 * @create 2020-03-27 09:43
 **/
public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "DarkRoast coffe";
    }
    @Override
    public double cost() {
        return 0;
    }
}

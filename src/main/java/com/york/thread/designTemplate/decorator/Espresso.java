package com.york.thread.designTemplate.decorator;

/**
 * 浓缩咖啡
 * @author york
 * @create 2020-03-27 09:41
 **/
public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}

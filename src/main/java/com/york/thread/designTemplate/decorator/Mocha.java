package com.york.thread.designTemplate.decorator;

/**
 * @author york
 * @create 2020-03-27 09:45
 **/
public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }
    @Override
    public String getDescription() {
        return beverage.getDescription() + "mocha";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.9;
    }
}

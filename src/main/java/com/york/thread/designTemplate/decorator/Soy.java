package com.york.thread.designTemplate.decorator;

/**
 * @author york
 * @create 2020-03-27 09:46
 **/
public class Soy extends CondimentDecorator {
    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }
    @Override
    public String getDescription() {
        return beverage.getDescription() + ",soy";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.7;
    }
}

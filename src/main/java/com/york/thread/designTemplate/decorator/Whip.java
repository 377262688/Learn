package com.york.thread.designTemplate.decorator;

/**
 * @author york
 * @create 2020-03-27 09:48
 **/
public class Whip extends CondimentDecorator {
    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }
    @Override
    public String getDescription() {
        return beverage.getDescription() + "whip";
    }

    @Override
    public double cost() {
        return 0.2 + beverage.cost();
    }
}

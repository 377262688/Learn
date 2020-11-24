package com.york.thread.designTemplate.celue;

/**
 * 策略模式
 * @author york
 * @create 2020-03-26 09:53
 **/
public class MainClass {

    public static void main(String[] args) {
        Duck mallardDuck = new MallardDuck();
        mallardDuck.performFly();
        mallardDuck.performQuack();

        mallardDuck.setFlyBehavior(new FlyNoWay());
        mallardDuck.setQuackBehavior(new Squack());

        mallardDuck.performFly();
        mallardDuck.performQuack();
    }
}

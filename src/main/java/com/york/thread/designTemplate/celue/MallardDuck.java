package com.york.thread.designTemplate.celue;

/**
 * @author york
 * @create 2020-03-26 09:40
 **/
public class MallardDuck extends Duck {

    public MallardDuck() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }
}

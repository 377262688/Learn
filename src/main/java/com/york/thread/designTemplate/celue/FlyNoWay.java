package com.york.thread.designTemplate.celue;

/**
 * @author york
 * @create 2020-03-26 09:47
 **/
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("鸭子不能飞");
    }
}

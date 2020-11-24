package com.york.thread.designTemplate.celue;

/**
 * @author york
 * @create 2020-03-26 09:49
 **/
public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("不会叫");
    }
}

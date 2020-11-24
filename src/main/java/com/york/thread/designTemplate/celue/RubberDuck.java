package com.york.thread.designTemplate.celue;

/**
 * @author york
 * @create 2020-03-26 09:44
 **/
public class RubberDuck extends Duck implements Quackable {

    @Override
    public void quack() {
        System.out.println("呱呱叫");
    }
}

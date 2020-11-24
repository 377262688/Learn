package com.york.thread.designTemplate.celue;

/**
 * @author york
 * @create 2020-03-26 09:48
 **/
public class Squack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("吱吱叫");
    }
}

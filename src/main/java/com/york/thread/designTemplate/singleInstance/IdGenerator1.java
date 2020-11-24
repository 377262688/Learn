package com.york.thread.designTemplate.singleInstance;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 饿汉单例模式
 * @author york
 * @create 2020-03-07 19:54
 **/
public class IdGenerator1 {

    private IdGenerator1() {}

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private static IdGenerator1 instance;
    public static synchronized IdGenerator1 getInstance() {
        if (instance == null) {
            instance = new IdGenerator1();
        }
        return instance;
    }

    public int getId() {
        return atomicInteger.incrementAndGet();
    }
}

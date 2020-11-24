package com.york.thread.designTemplate.singleInstance;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 饿汉单例模式
 * @author york
 * @create 2020-03-07 19:52
 **/
public class IdGnerator0 {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private static final IdGnerator0 instance = new IdGnerator0();

    private IdGnerator0() {}

    public static IdGnerator0 getInstance() {
        return instance;
    }

    public Integer getId() {
        return atomicInteger.incrementAndGet();
    }
}

package com.york.thread.designTemplate.singleInstance;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 静态内部类
 * @author york
 * @create 2020-03-07 20:01
 **/
public class IdGenerator3 {

    private IdGenerator3() {}

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private static class SingleHolder {
        private static final IdGenerator3 instance = new IdGenerator3();
    }
    public static IdGenerator3 getInstance() {
        return SingleHolder.instance;
    }

    public int getId() {
        return atomicInteger.incrementAndGet();
    }
}

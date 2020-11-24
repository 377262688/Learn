package com.york.thread.designTemplate.singleInstance;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 双重检查
 * @author york
 * @create 2020-03-07 19:59
 **/
public class IdGenerate2 {


    private IdGenerate2() {}

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private static IdGenerate2 instance;
    public static IdGenerate2 getInstance() {
        if (instance == null) {
            synchronized (IdGenerate2.class) {
                if (instance == null) {
                    instance = new IdGenerate2();
                }
            }
        }
        return instance;
    }

    public int getId() {
        return atomicInteger.incrementAndGet();
    }
}

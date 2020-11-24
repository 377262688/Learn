package com.york.thread.wangyiJava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author york
 * @create 2019-09-28 18:33
 **/
public class LongAdderTest {

    private static LongAdder longAdder = new LongAdder();

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static int i = 0;

    public static void testSync() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int j=0;j<2;j++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    long startTime = System.currentTimeMillis();
                    while (System.currentTimeMillis() - startTime < 2000) {
                        synchronized (LongAdderTest.class) {
                            i++;
                        }
                    }
                }
            });
            thread.start();
            threads.add(thread);
        }

        for (Thread thread:threads) {
            thread.join();
        }
        System.out.println("执行次数：" + i);
    }

    public static void testAtomicInteger() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int j=0;j<2;j++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    long startTime = System.currentTimeMillis();
                    while (System.currentTimeMillis() - startTime < 2000) {
                        atomicInteger.getAndIncrement();
                    }
                }
            });
            thread.start();
            threads.add(thread);
        }

        for (Thread thread:threads) {
            thread.join();
        }
        System.out.println("atomicInterger执行耗时："  + "执行次数：" + atomicInteger.get());
    }

    public static void testLongAdder() throws InterruptedException {

        List<Thread> threads = new ArrayList<>();
        for (int j=0;j<2;j++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    long startTime = System.currentTimeMillis();
                    while (System.currentTimeMillis() - startTime < 2000) {
                        longAdder.increment();
                    }
                }
            });
            thread.start();
            threads.add(thread);
        }

        for (Thread thread:threads) {
            thread.join();
        }
        System.out.println("longAdder执行耗时："  + "执行次数：" + longAdder.longValue());
    }

    public static void main(String[] args) throws InterruptedException {
        testSync();
        testAtomicInteger();
        testLongAdder();
    }
}

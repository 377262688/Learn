package com.york.thread.wangyiJava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author york
 * @create 2019-09-28 17:45
 **/
public class Demo2 {

    private volatile static int x = 0;
    AtomicInteger atomicInteger = new AtomicInteger(0);
//    static Unsafe unsafe = null;
//
//    static {
//        try {
//            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
//            theUnsafe.setAccessible(true);
//            unsafe = (Unsafe) theUnsafe.get(null);
//
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
    private static LongAdder x1 = new LongAdder();
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int j = 0;j<2;j++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0;i<10000;i++ ) {
                            Demo2.x1.increment();

                    }
                }
            });
            threads.add(thread);
            thread.start();
        }
        for (Thread thread:threads) {
            thread.join();
            thread.join();
        }
        System.out.println(x1);


    }
}

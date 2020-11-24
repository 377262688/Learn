package com.york.thread.multyThread.threadLocl;

import io.netty.util.internal.ConcurrentSet;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author york
 * @create 2020-11-24 09:20
 **/
public class ThreadLocalTest {


    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static ConcurrentSet<String> concurrentSet = new ConcurrentSet<>();
    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();
    private static ConcurrentSet<String> concurrentSet1 = new ConcurrentSet<>();


    private static SimpleDateFormatThreadLocal simpleDateFormatThreadLocal = new SimpleDateFormatThreadLocal("yyyy/MM/dd HH:mm:ss");
    private static ConcurrentSet<String> concurrentSet2 = new ConcurrentSet<>();

    private static void noThreadLoclTest(Date date) {
        concurrentSet.add(simpleDateFormat.format(date));
    }

    private static void threadLocalTest(Date date) {
        SimpleDateFormat sdf = threadLocal.get();
        if (null == sdf) {
            threadLocal.set(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
        }
        concurrentSet1.add(threadLocal.get().format(date));
        threadLocal.remove();
    }

    private static void threadLocalTest1(Date date) {
        String dateStr = simpleDateFormatThreadLocal.get().format(date);
        concurrentSet2.add(dateStr);
        simpleDateFormatThreadLocal.remove();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("当前时间：" );
        noThreadLoclTest(new Date());
        List<Thread> threads = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            Thread thread = new Thread(()-> {
                Date date = new Date(System.currentTimeMillis() - finalI * 1000 * 60 * 60 * 24);
                noThreadLoclTest(date);
                threadLocalTest(date);
                threadLocalTest1(date);
            });
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("set size:" + concurrentSet.size());
        System.out.println("set1 size:" + concurrentSet1.size());
        System.out.println("set2 size:" + concurrentSet2.size());

        WeakReference<Thread> threadWeakReference = new WeakReference<>(new Thread("name"));
        System.out.println("回首前" + threadWeakReference.get());
        System.gc();
        System.out.println("回收后" + threadWeakReference.get());
    }
}

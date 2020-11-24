package com.york.thread.multyThread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写两个线程，一个线程打印152，另一个线程打印AZ，打印顺序是12A34B...5152Z；
 * @author york
 * @create 2019-12-09 20:40
 **/
public class ThreadTest2 {
    private static volatile boolean flag = false;
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static StringBuilder stringBuffer = new StringBuilder("");
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,5,5, TimeUnit.SECONDS,new LinkedBlockingQueue<>(100));
        poolExecutor.execute(() -> {
            for (int i = 1; i < 27; i++) {
                lock.lock();
                while (flag) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                flag = true;
                stringBuffer.append(i * 2 - 1);
                stringBuffer.append(i * 2);
                condition.signal();
                lock.unlock();
            }
        });
        poolExecutor.execute(() -> {
            for (int i = 0; i < 26; i++) {
                lock.lock();
                while (!flag) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                flag = false;
                stringBuffer.append((char)(i + 'A'));
                condition.signal();
                lock.unlock();
            }
        });
        System.out.println("dsa");
        Thread.sleep(10000);
        System.out.println(stringBuffer.toString());
    }
}

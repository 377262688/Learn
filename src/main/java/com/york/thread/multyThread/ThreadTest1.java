package com.york.thread.multyThread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 子线程循环2次，主线程循环2次，然后子线程循环2次，主线程循环2次，这样循环10次
 *
 * @author york
 * @create 2019-12-09 17:57
 **/
public class ThreadTest1 {
    private boolean flag = true;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
    public synchronized void sub() {
        while (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = false;
        for (int i = 0; i < 2; i++) {
            System.err.println("sub run ....");
        }
        this.notify();
    }

    public synchronized void main() {
        while (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = true;
        for (int i = 0; i < 2; i++) {
            System.err.println("main run ....");
        }
        this.notify();
    }


    public void sub1() {
        lock.lock();
        try {
            while (!flag) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            flag = false;
            for (int i = 0; i < 2; i++) {
                System.err.println("sub run ....");
            }
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void main1() {
        lock.lock();
        try {
            while (flag) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            flag = true;
            for (int i = 0; i < 2; i++) {
                System.err.println("main run ....");
            }
            condition.signal();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        ThreadTest1 threadTest1 = new ThreadTest1();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                threadTest1.sub1();
            }
        });
        thread.start();
        for (int i = 0; i < 10; i++) {
            threadTest1.main1();
        }
    }
}

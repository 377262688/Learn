package com.york.thread.wangyiJava;

/**
 * @author york
 * @create 2019-10-19 10:09
 * 死锁测试用例
 **/
public class DeadLockDemo {

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private void deadLockTest() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                synchronized (lock1) {
                    System.out.println("lock1被线程"  + Thread.currentThread().getId() + "拿到了");
                    try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2) {
                        System.out.println("lock2被线程"  + Thread.currentThread().getId() + "拿到了");
                    }
                }
            }
        });
        thread.start();
        while (true) {
            Thread.sleep(2000L);
            synchronized (lock2) {
                System.out.println("lock2被线程"  + Thread.currentThread().getId() + "拿到了");
                synchronized (lock1) {
                    System.out.println("lock1被线程"  + Thread.currentThread().getId() + "拿到了");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new DeadLockDemo().deadLockTest();
    }
}

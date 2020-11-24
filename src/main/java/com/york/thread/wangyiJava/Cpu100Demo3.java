package com.york.thread.wangyiJava;

import java.util.concurrent.locks.LockSupport;

/**
 * @author york
 * @create 2019-10-19 09:54
 * wait/notify机制，notify之后再进入的wait不会收到通知
 * park/unpark 不管先后顺序，都会执行
 **/
public class Cpu100Demo3 {

    private volatile static Object baozidian = null;

    public void waitLockTest() throws InterruptedException {
        Thread thread = new Thread(() -> {
            // 如果为空，进入等待
            if (baozidian == null) {
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (this) {
                    try {
                        System.out.println("1.进入等待，线程ID为：" + Thread.currentThread().getId());
                        LockSupport.park();
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        thread.start();

        Thread.sleep(3000L);
        baozidian = new Object();
        synchronized (this) {
            this.notifyAll();
//            LockSupport.unpark(thread);
            System.out.println("通知消费者");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Cpu100Demo3().waitLockTest();
    }
}

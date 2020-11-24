package com.york.thread.service;

import lombok.Data;

import java.util.concurrent.*;

/**
 * Created by york on 2019/3/28.
 * 待研究的模块：
 *    BlockingQueue
 *    Fork()/Join()
 *    ConcurrentLinkedQueue
 *    ConcurrentSkipListMap
 */
public class ThreadTest {
    private static User user = new User();
    @Data
    public static class User {
        private int id;
        private String name;
        public User() {
            this.id = 0;
            this.name = "0";
        }
        @Override
        public String toString() {
            return "User{id=" +user.getId() + ",name="+user.getName() +"}";
        }
    }

    public static class WriteThread extends Thread {

        private boolean stopMe = false;
        public void stopMe() {
            stopMe = true;
        }
        @Override
        public void run() {
            while (true) {
                if (stopMe) {
                    break;
                }
                synchronized (user) {
                    int v = (int) (System.currentTimeMillis()/1000);
                    user.setId(v);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    user.setName(String.valueOf(v));
                }
                Thread.yield();
            }
        }
    }

    public static class ReadThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (user) {
                    if (user.getId() != Integer.valueOf(user.getName())) {
                        System.out.println(user.toString());
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
//        ArrayBlockingQueue arrayBlockingQueue;
//        WriteThread thread = new WriteThread();
//        Thread thread1 = new Thread(new ReadThread());
//        thread.start();
//        thread1.start();
//        thread.stopMe();
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i=0;i<5;i++) {
            Future f = pool.submit(new DivTask(100,i));
            try {
                f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static class DivTask implements Runnable {

        private int a,b;

        public DivTask(int a,int b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public void run() {
            double re = a/b;
            System.out.println(re);
        }
    }

}

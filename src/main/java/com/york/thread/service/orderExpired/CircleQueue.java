package com.york.thread.service.orderExpired;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 环形队列实现订单过期
 *
 * @author york
 * @create 2019-11-10 11:49
 **/
public class CircleQueue {
    private static final int SLOT_NUM = 60;
    private static ArrayList<LinkedList<Task>> queue = new ArrayList<>(SLOT_NUM);
    private static volatile int curNum = 0;

    public static void main(String[] args) {
        for (int i = 0; i < SLOT_NUM; i++) {
            queue.add(i,new LinkedList<>());
        }
        new Thread(new ProduceThread()).start();

    }
    static class ProduceThread implements Runnable {

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                Long orderId = Long.valueOf(i);
                int num = i*10/60 + curNum;
                Task task = new Task(orderId,i*10,num);
                int slot = task.getExpired()%SLOT_NUM;
                queue.get(slot).add(task);
            }
        }
    }

    static class ConsumerThread implements Runnable {

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            while (true) {
                for (int i = 0; i < SLOT_NUM; i++) {
                    LinkedList<Task> tasks = queue.get(i);
                    Iterator<Task> iterator = tasks.iterator();
                    while (iterator.hasNext()){
                        Task task = iterator.next();
                        if (task.getNum() <= curNum) {
                            System.out.println("执行订单：" + task.getOrderId());
                            tasks.remove(task);
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                curNum += 1;
            }
        }
    }

    @Data
    @AllArgsConstructor
    static class Task {
        private Long orderId;
        private int expired;
        private int num;
    }
}

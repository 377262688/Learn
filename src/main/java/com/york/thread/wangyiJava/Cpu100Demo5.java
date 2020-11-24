package com.york.thread.wangyiJava;

import java.util.Random;

/**
 * @author york
 * @create 2019-10-19 10:28
 **/
public class Cpu100Demo5 {

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            // 死循环，打满cpu
            while (true) {
                new Random().nextInt(100);
            }
        },"CPU-high").start();

        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    int x = 0;
                    for (int j = 0; j < 10000; j++) {
                        x = x + 1;
                        long random = new Random().nextInt(100);
                        Thread.sleep(random); // 模拟处理耗时
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            long random = new Random().nextInt(500);
            Thread.sleep(random); // 模拟接口调用耗时
        }
    }
}

package com.york.thread.wangyiJava;

import java.util.ArrayList;

/**
 * @author york
 * @create 2019-10-18 21:22
 **/
public class OutOfMemeryDemo1 {

    static ArrayList<Object> objects = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            objects.add(new byte[1024 * 1024 * 64]);
            Thread.sleep(3000L);
        }
    }
}

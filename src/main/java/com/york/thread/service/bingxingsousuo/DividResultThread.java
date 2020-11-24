package com.york.thread.service.bingxingsousuo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by york on 2019/3/29.
 */
public class DividResultThread implements Runnable {

    public static BlockingQueue<Msg> queue = new LinkedBlockingQueue<>();
    @Override
    public void run() {
        while (true) {
            try {
                Msg msg = queue.take();
                System.out.println("结果" + msg.getA()/2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

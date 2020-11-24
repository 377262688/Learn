package com.york.thread.service.bingxingsousuo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by york on 2019/3/29.
 */
public class MultiplyThread implements Runnable {

    public static BlockingQueue<Msg> queue = new LinkedBlockingQueue<>();


    @Override
    public void run() {
        while (true) {
            Msg msg = null;
            try {
                msg = queue.take();
                msg.setA(msg.getB()*msg.getA());
                DividResultThread.queue.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

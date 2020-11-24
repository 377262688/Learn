package com.york.thread.service.bingxingsousuo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by york on 2019/3/29.
 */
public class PlusThread implements Runnable{

    public static BlockingQueue<Msg> queue = new LinkedBlockingQueue<Msg>();
    @Override
    public void run() {
        while (true) {
            try {
                Msg msg = queue.take();
                msg.setB(msg.getA() + msg.getB());
                MultiplyThread.queue.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T getT(T t) {
        T T1 = t;
        return T1;
    }
}

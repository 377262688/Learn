package com.york.thread.service.bingxingsousuo;

/**
 * Created by york on 2019/3/29.
 */
public class Main {

    public static void main(String[] args) {
        new Thread(new PlusThread()).start();
        new Thread(new MultiplyThread()).start();
        new Thread(new DividResultThread()).start();
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++ ) {
                PlusThread.queue.add(new Msg(i,j));
            }
        }
    }
}

package com.york.thread.wangyiJava;

/**
 * @author york
 * @create 2019-10-17 19:26
 **/
public class GcTest {


    public static void main(String[] args) {

        System.out.println(System.currentTimeMillis() - 1*3600000 - 5*60*1000);
//        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
//            new Thread(() -> {
////                for (int i = 0; i < 150; i++) {
//                for (int i = 0; i < 1000; i++) {
//                    try {
//                        byte[] temp = new byte[1024*512];
////                        Thread.sleep(1000);
//                        Thread.sleep(50);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        },100,100, TimeUnit.MILLISECONDS);
    }
}

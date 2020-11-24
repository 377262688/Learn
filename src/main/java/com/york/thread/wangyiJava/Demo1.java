package com.york.thread.wangyiJava;

/**
 * @author york
 * @create 2019-09-28 16:37
 **/
public class Demo1 {

    private Boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Demo1 demo1 = new Demo1();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (demo1.flag) {
                    synchronized (demo1.flag) {
                        i++;
                    }
                }
                System.out.println("输出完成了，i的值为：" + i);
            }
        });
        thread.start();
        thread.interrupt();
        Thread.sleep(200);
        demo1.flag = false;
        System.out.println("flag被改为false了");
    }
}

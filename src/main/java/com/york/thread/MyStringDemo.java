package com.york.thread;

import java.util.ArrayList;
import java.util.Collections;

public class MyStringDemo {

    private String string;

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public void setString(String string) {
        threadLocal.set(string);
        this.string = string;
    }

    public String getString() {
        return threadLocal.get();
    }

    public static void main(String[] args) {
        Collections.synchronizedList(new ArrayList<>());
        System.out.println(System.currentTimeMillis() - 3600000 - 5*60*1000);
//        String s = "pages/index?homeConfig={\"type\":1,\"homeConfig\":\"1173508247711866882\"}";
//        String json = s.split("\\?homeConfig=")[1];
//        System.out.println(json);
////        String s = "@{user.nickname}\n" +
////                "\n" +
////                "你好，这是昵称测试";
////        System.out.println(s.replaceAll("\\{user.nickname\\}","123"));
//        String a = "123";
//        String b = "123";
//        System.out.println(a==b);
//        Object o1 = new Object();
//        Object o2 = new Object();
//        System.out.println(o1 == o2);
//        System.out.println(optimalNumOfBits(100000000,0.00001));
    }

    private static long optimalNumOfBits(long n, double p) {
        if (p == 0.0D) {
            p = 4.9E-324D;
        }

        return (long)((double)(-n) * Math.log(p) / (Math.log(2.0D) * Math.log(2.0D)));
    }
}

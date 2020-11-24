package com.york.thread.util;

/**
 * @author york
 * @create 2020-03-31 17:02
 **/
public class HashMapTest {

    public static void main(String[] args) {
        int initialCapacity = 8;
        System.out.println(tableSizeFor(16));
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        System.out.println(n);
        System.out.println(n >>> 1);
        System.out.println(n | 7);
        n |= n >>> 1;
        System.out.println(n);

        n |= n >>> 2;
        System.out.println(n);

        n |= n >>> 4;
        System.out.println(n);

        n |= n >>> 8;
        System.out.println(n);

        n |= n >>> 16;
        System.out.println(n);

        return (n < 0) ? 1 : (n >= 1 << 30) ? 1 << 30 : n + 1;
    }
}

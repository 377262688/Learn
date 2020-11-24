package com.york.thread.jvm;

/**
 * 测试内存分配
 *
 * @author york
 * @create 2020-03-14 21:38
 **/
public class TestAllocastion {

    private static final int _1MB = 1024 * 1024;

    private static void testAllocation() {
        byte[] bytes = new byte[2 *_1MB];
        byte[] bytes1 = new byte[2 *_1MB];
        byte[] bytes2 = new byte[2 *_1MB];
        byte[] bytes3 = new byte[4 *_1MB];
    }

    public static void main(String[] args) {
        testAllocation();
    }
}

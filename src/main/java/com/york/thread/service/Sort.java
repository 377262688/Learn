package com.york.thread.service;

import java.util.concurrent.CountDownLatch;

/**
 * Created by york on 2019/4/1.
 */
public class Sort {

    private static int[] ints = {3,4,5,1,54,1,32,543,32,12,65,99};

    /**
     * 冒泡排序
     * @param ints
     */
    public static void sort1(int[] ints) {
        for (int i = 0;i<ints.length;i++) {
            for (int j=0;j<ints.length;j++) {
                int a,b;
                if (j == ints.length - 1) {
                    continue;
                }
                if (ints[j] > ints[j+1]) {
                    a = ints[j];
                    b = ints[j+1];
                    ints[j] = b;
                    ints[j+1] = a;
                }
            }
        }
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

    /**
     * 奇偶交换排序
     * @param ints
     */
    public static void sort2(int[] ints) {
        int excFlag = 1;
        int start = 0;
        while (excFlag == 1 || start == 1) {
            excFlag = 0;
            for (int i = start;i<ints.length - 1;i = i+2) {
                if (ints[i] > ints[i+1]) {
                    int tem;
                    tem = ints[i+1];
                    ints[i+1] = ints[i];
                    ints[i] = tem;
                    excFlag = 1;
                }
            }
            if (start == 0) {
                start = 1;
            } else {
                start = 0;
            }
        }
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
    private static int excFlag = 1;
    public static synchronized void setExcFlag(int excFlag) {
        Sort.excFlag = excFlag;
    }
    public static synchronized int getExcFlag() {
        return excFlag;
    }

    /**
     * 多线程奇偶交换排序
     */
    public static void sort5(int[] arr) throws InterruptedException {
        int start = 0;
        while (getExcFlag() == 1 || start == 1) {
            setExcFlag(0);
            CountDownLatch latch = new CountDownLatch(arr.length/2 - arr.length%2==0 ? start: 0);
            for (int i = start;i<arr.length -1;i+=2) {
                new Thread(new Sort4(i,latch,arr)).start();
            }
            latch.await();
            if (start == 0) {
                start = 1;
            } else {
                start = 0;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


    public static class Sort4 implements Runnable {
        int i;
        CountDownLatch latch;
        int[] arr;
        public Sort4(int i,CountDownLatch latch,int[] arr) {
            this.i = i;
            this.latch = latch;
            this.arr = arr;
        }
        @Override
        public void run() {
            if (arr[i] > arr[i+1]) {
                int tem = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = tem;
                setExcFlag(1);
            }
            latch.countDown();
        }
    }

    /**
     * 插入排序
     * @param array
     */
    public static void insertionSort(int[] array) {
        for (int i=1;i<array.length;i++) {
            int temp = array[i];
            int j = i-1;
            for (;j>=0;j--) {
                if (temp<array[j]) {
                    array[j+1] = array[j];
                } else {
                    array[j] = temp;
                }
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
//        sort1(ints);
//        sort2(ints);
//        sort5(ints);
        insertionSort(ints);
        for (int i=0;i<ints.length;i++) {
            System.out.println(ints[i]);
        }
    }
}

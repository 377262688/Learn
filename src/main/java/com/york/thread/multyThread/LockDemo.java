package com.york.thread.multyThread;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author york
 * @create 2020-11-24 11:01
 **/
public class LockDemo {

    private static ConcurrentHashMap<Long, Object> orderIds = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<Long, AtomicInteger> orderGrouCount = new ConcurrentHashMap<>();

    public void join(Long orderId, Long newOrderId) {
        Object orderLock = orderIds.computeIfAbsent(orderId, v -> new Object());
        synchronized (orderLock) {
            AtomicInteger count = getCount(orderId);
            if (count.get() > 2) {
                joinNewGroupOrder(newOrderId);
            } else {
                joinThisGroupOrder(orderId, newOrderId);
            }
        }
    }

    private void joinThisGroupOrder(Long orderId, Long newOrderId) {
        AtomicInteger count = getCount(orderId);
        count.incrementAndGet();
    }

    private void joinNewGroupOrder(Long newOrderId) {
        // 产生一个新的订单，然后加入该订单
        Long orderId = new Random().nextLong();
        join(orderId, newOrderId);
    }

    private AtomicInteger getCount(Long orderId) {
        // 模拟查询数据库订单
        return orderGrouCount.getOrDefault(orderId, new AtomicInteger());
    }

    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 12, TimeUnit.MINUTES, new LinkedBlockingQueue<>(100));
        CompletionService<Void> completionService = new ExecutorCompletionService<>(executorService);
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            completionService.submit(() -> {
                Thread thread = new Thread(() -> {
                    lockDemo.join(1L, Long.valueOf(finalI));
                });
                thread.start();
                thread.join();
                System.out.println("线程" + finalI + "执行完成");
                countDownLatch.countDown();
                return null;
            });
        }
        try {
            countDownLatch.await(Long.MAX_VALUE,TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(orderGrouCount);
    }
}

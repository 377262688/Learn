package com.york.thread.multyThread;

import java.util.concurrent.*;

/**
 * @author york
 * @create 2019-12-09 10:02
 * 烧水泡茶
 **/
public class Shaoshuipaocha {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> t1 = new FutureTask<>(new T1Task());
        FutureTask<String> t2 = new FutureTask<>(new T2Task(t1));
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,5,5L,
                TimeUnit.SECONDS,new LinkedBlockingQueue<>(10));
        threadPoolExecutor.submit(t1);
        String result = null;
        threadPoolExecutor.submit(t2,result);
    }

    static class T1Task implements Callable<String> {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public String call() throws InterruptedException {
            System.out.println("T2: 洗茶壶...");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T2: 洗茶杯...");
            TimeUnit.SECONDS.sleep(2);

            System.out.println("T2: 拿茶叶...");
            TimeUnit.SECONDS.sleep(1);
            return " 龙井 ";
        }
    }

    static class T2Task implements Callable<String> {

        private FutureTask<String> t1Task;

        public T2Task(FutureTask<String> t1Task) {
            this.t1Task = t1Task;
        }
        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public String call() throws InterruptedException, ExecutionException {
            System.out.println("T2: 洗水壶...");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T2: 烧开水...");
            TimeUnit.SECONDS.sleep(15);
            // 获取 T2 线程的茶叶
            String tf = t1Task.get();
            System.out.println("T2: 拿到茶叶:"+tf);

            System.out.println("T2: 泡茶...");
            return " 上茶:" + tf;
        }
    }
}

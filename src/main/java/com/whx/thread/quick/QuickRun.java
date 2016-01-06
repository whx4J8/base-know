package com.whx.thread.quick;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wanghongxing on 15/12/13.
 */
public class QuickRun {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(getFirstRunThread());
    }

    public static Integer getFirstRunThread() throws ExecutionException, InterruptedException {

        List<Future<Integer>> futures = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        futures.add(threadPool.submit(() -> {//异步
            Thread.sleep(2 * 1000);//可能执行快可能执行慢
            countDownLatch.countDown();
            return new Integer(2);
        }));

        futures.add(threadPool.submit(() -> {//异步
            Thread.sleep(3 * 1000);
            countDownLatch.countDown();
            return new Integer(3);
        }));

        countDownLatch.await();//主线程等待执行较快的线程结果

        Integer result = null;
        for(Future<Integer> future : futures){
            if(future.isDone()){
                result = future.get();
            }
        }
        return result;
    }

}

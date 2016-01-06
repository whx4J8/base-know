package com.whx.thread.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 多线程加速
 * 同时不影响主线程
 *
 * Created by wanghongxing on 15/12/19.
 */
public class TestCountDownLatch {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(5);

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for(int i=0;i<5;i++){

            threadPool.submit(() -> {

                try {
                    Thread.sleep(2*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("is done");
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        System.out.println("is all done");

    }

}

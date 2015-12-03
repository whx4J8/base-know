package com.whx.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 使用java 中的关键字synchronized
 * 做同步代码块
 *
 * Created by wanghongxing on 15/12/3.
 */
public class TestSynchronized {

    public static class Counter{
        private int count = 0;
        private Object lock = new Object();

        public void inc(){
            synchronized (lock){
                count = count + 1;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for(int i=0;i<10;i++){
            threadPool.execute(() -> {
                counter.inc();
            });
        }

        Thread.sleep(10);
        System.out.println(counter.count);
    }


}

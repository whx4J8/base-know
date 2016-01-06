package com.whx.thread.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wanghongxing on 15/12/19.
 */
public class ThreadEachRun {

    private static CountDownLatch thread1Latch = new CountDownLatch(1);
    private static CountDownLatch thread2Latch = new CountDownLatch(1);
    private static AtomicInteger runTime = new AtomicInteger(0);
    private static Object lock = new Object();

    public static void main(String[] args){

        Thread thread1 = new Thread1();
        Thread thread2 = new Thread2();
        thread1.start();
        thread2.start();

    }

    public static class Thread1 extends Thread {

        @Override
        public void run() {

            thread1Latch.countDown();
            try {
                while(true){
                    thread1Latch.await();
                    synchronized (lock){
                        System.out.println(" thread1 run " + runTime.incrementAndGet() );
                        thread2Latch.countDown();
                        thread1Latch = new CountDownLatch(1);
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    public static class Thread2 extends Thread {

        @Override
        public void run() {

            try {
                while(true){
                    thread2Latch.await();
                    synchronized (lock){
                        System.out.println(" thread2 run " + runTime.incrementAndGet() );
                        thread1Latch.countDown();
                        thread2Latch = new CountDownLatch(1);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}

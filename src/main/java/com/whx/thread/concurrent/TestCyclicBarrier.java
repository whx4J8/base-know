package com.whx.thread.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wanghongxing on 15/12/19.
 */
public class TestCyclicBarrier {

    public static void main(String[] args){

        CyclicBarrier barrier = new CyclicBarrier(3);

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for(int i=0;i<3;i++){
            threadPool.submit(new Runner(barrier,"thread " + i));
        }

    }

    public static class Runner implements Runnable{

        private CyclicBarrier barrier = null;
        private String name = null;

        public Runner(CyclicBarrier barrier, String name) {
            this.barrier = barrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000 * new Random().nextInt(8));
                System.out.println(name + " ready ok");
                barrier.await();//只要到了等待的位置就行
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println(name + " run");

        }



    }



}

package com.whx.thread.barrier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CylicBarrier可以说比CountDownlatch实现了更多的内容
 *      1.循环使用
 *      2.主线程回调函数(主线程回调结束之后才能,子线程才能继续执行)
 *
 * CountDownLatch只能使用一次
 *
 * Created by wanghongxing on 16/1/1.
 */
public class TestCylicBarrier {

    public static void main(String[] args){

        int length = 6;
        int[] nums = new int[length];
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {//栅栏到达后触发的回调函数,回调函数执行后,各个线程继续执行
            int sum =0;
            for (int num : nums) {
                sum=sum+num;
            }

            try {
                Thread.sleep(4*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("the sum is" + sum);
        });

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for(int i=0;i<length;i++){
            threadPool.submit(new ComponentRunnable(barrier,i,nums));
        }

    }

    public static class ComponentRunnable implements Runnable{

        private CyclicBarrier barrier;
        private int id;
        private int[] array;

        public ComponentRunnable(CyclicBarrier barrier, int id, int[] array) {
            this.barrier = barrier;
            this.id = id;
            this.array = array;
        }

        @Override
        public void run() {
            try {

                array[id] = new Random().nextInt(100);
                System.out.println("index is " + id +" value is " + array[id] + " is wait");
                //wait
                barrier.await();
                System.out.println("index is " + id +" value is " + array[id] + " is wake");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}

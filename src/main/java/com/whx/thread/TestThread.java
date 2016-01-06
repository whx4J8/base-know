package com.whx.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wanghongxing on 15/12/9.
 */
public class TestThread {

    public static int num =0;//

    public static class ThreadDemo extends Thread{

        public static Object lock = new Object();

        @Override
        public void run() {

            for(int i=0;i<1000;i++){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (this){//锁不住滴
                    TestThread.num++;
                    System.out.println(this.currentThread() + " " + TestThread.num + " " + this);
                }

            }

        }

    }

    public static void main(String[] args){

        AtomicInteger integer = new AtomicInteger(0);

        ThreadDemo thread = new ThreadDemo();
        ThreadDemo thread2 = new ThreadDemo();

        thread.start();
        thread2.start();

    }



}

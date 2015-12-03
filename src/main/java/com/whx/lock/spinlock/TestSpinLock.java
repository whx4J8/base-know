package com.whx.lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * 自旋锁不是公平锁,不会按照进入lock的顺序进行获取锁
 *      特点:不进行thread状态的改变,响应速度更快,但当等待锁的线程数量不停增加,性能下降,因为每个线程都需要执行,占用cpu时间片
 *      场景:线程竞争不激烈,并且保持锁的时间短,适合使用自旋锁
 *
 * Created by wanghongxing on 15/12/3.
 */
public class TestSpinLock {

    /**
     * 自旋锁
     */
    public static class SpinLock{

        private AtomicReference<Thread> sign = new AtomicReference<>();

        /**
         * 获得锁
         */
        public void lock(){
            Thread current = Thread.currentThread();
            while(!sign.compareAndSet(null,current)){
            }
        }

        /**
         * 解除锁
         */
        public void unlock(){
            Thread current = Thread.currentThread();
            sign.compareAndSet(current,null);
        }

    }

    /**
     *
     */
    public static class Run implements Runnable{

        @Override
        public void run() {
            SpinLock lock = new SpinLock();
            lock.lock();
            integer = integer + 1;
            lock.unlock();
        }
    }

    /**
     * 外部的静态引用,线程会同时对该值进行修改
     */
    public static Integer integer = 1;


    /**
     * 测试自旋锁
     * @param args
     */
    public static void main(String[] args){

        Thread thread1 = new Thread(new Run());
        Thread thread2 = new Thread(new Run());
        Thread thread3 = new Thread(new Run());
        Thread thread4 = new Thread(new Run());
        Thread thread5 = new Thread(new Run());

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        System.out.println(integer);
    }

}

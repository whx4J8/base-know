package com.whx.lock.timelock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 使用 ReentrantLock 代替内置锁 实现带时间阻塞的锁
 *
 * Created by wanghongxing on 16/1/7.
 */
public class TestTimeLock {

    /**
     * 公共的资源
     */
    public static int PUBLIC_NUM = 1;

    /**
     * lock
     */
    private static Lock lock = new ReentrantLock();


    public static void main(String[] args){
        testTryLock();
    }

    /**
     * 测试ReentrantLock的tryLock方法,并不会阻塞
     *      tryLock();      不会阻塞,立即返回
     *
     *      tryLock(time);  会阻塞,但是会阻塞time时间,若到时间还未获取到锁的占有,则返回false
     *
     */
    public static void testTryLock(){
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for(int i=0;i<3;i++){
            threadPool.execute(() -> {

                boolean isGetLock = false;
                try {
                    isGetLock = lock.tryLock(4, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println( " the thread " + Thread.currentThread().getName() + " is get lock " + isGetLock);
                if(isGetLock==false){
                    return;
                }

                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println(" the thread " + Thread.currentThread().getName() + " is release lock");
                }

            });
        }
    }


    /**
     * 测试ReentrantLock的lock方法,会阻塞
     */
    public static void testLock(){
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for(int i=0;i<3;i++){
            threadPool.execute(() -> {

                //boolean isGetLock = lock.tryLock();//不会阻塞
                //System.out.println( " the thread " + Thread.currentThread().getName() + " is get lock " + isGetLock);
                lock.lock();
                System.out.println( " the thread " + Thread.currentThread().getName() + " is get lock ");
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println(" the thread " + Thread.currentThread().getName() + " is release lock");
                }

            });
        }
    }

}

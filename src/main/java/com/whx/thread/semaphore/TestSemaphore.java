package com.whx.thread.semaphore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 使用信号量完成阻塞队列
 *      实现队列操作的阻塞操作
 *
 * Created by wanghongxing on 15/12/30.
 */
public class TestSemaphore {

    public static void main(String[] args) throws InterruptedException {

        BoundedList<Integer> list = new BoundedList<>(5);

        List<Thread> threads = new ArrayList<>();
        for(int i=0;i<10;i++){
            final Integer num = i;
            threads.add(new Thread(() -> {
                try {
                    list.add(new Integer(num));
                    System.out.println("put to boundedlist " + num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }

        threads.forEach(thread-> thread.start());
        Thread.sleep(1000 * 5);        //main thread wait
        for(int i=0;i<5;i++){          //remove之后,被阻塞的队列操作被唤醒
            list.remove(i);
        }

    }

    /**
     * 线程安全的阻塞队列实现类
     * @param <T>
     */
    public static class BoundedList<T>{

        private Semaphore sem;
        private List<T> list = null;

        public BoundedList(int count) {
            list = Collections.synchronizedList(new ArrayList<>());
            sem = new Semaphore(count);
        }

        public boolean add(T o) throws InterruptedException {
            sem.acquire();                      //acquire
            boolean wasAdded = false;
            try{
                wasAdded = list.add(o);
                return wasAdded;
            }finally{
                if(!wasAdded){
                    sem.release();              //release
                }
            }
        }

        public boolean remove(T o){
            boolean isRemoved = list.remove(o);
            if(isRemoved){
                sem.release();                  //release
            }
            return isRemoved;
        }
    }

}

package com.whx.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by wanghongxing on 15/12/3.
 */
public class TestAtomicRefInMultThread {

    /**
     * 线程外部静态持有一个AtomicReference对象
     */
    private static AtomicReference<Integer> atomicReference = new AtomicReference<>();

    public static void main(String[] args){

        //初始化一个值
        atomicReference.set(1);

        List<Thread> threads = new ArrayList<>();

        for(int i=0;i<10;i++){
            Thread thread = new AtomicRefThread();
            threads.add(thread);
        }

        threads.forEach(thread->{
            thread.start();
        });

        /**
         *
         * 执行结果:
         * update res true value 2
         * update res true value 3
         * update res false value 3
         * update res false value 3
         * update res false value 3
         * update res false value 3
         * update res false value 3
         * update res false value 3
         * ...
         *
         * thread 1 : 第一个拿到value=1 toUpdateValue=2 ,调用compareAndSet时候value没有被人改变过
         * thread 2 : 第二个拿到value=2 toUpdateValue=3 ,Thread.sleep                    这时候thread被唤醒,调用compareAndSet
         *            value还是1,更新成功
         *      thread 3: 第三个拿到value=2 toUpdateValue=3 , Thread.sleep                   唤醒,调用compareAndSet
         *                AtomicReference中的value为3,与thread中的value2不同,更新失败
         *          thread 4: 第三个拿到value=2 toUpdateValue=3 , Thread.sleep               ..
         *              thread 5: 第三个拿到value=2 toUpdateValue=3 , Thread.sleep           ..
         *                  thread 6: 第三个拿到value=2 toUpdateValue=3 , Thread.sleep       ..
         *      ...
         *
         */

    }


    /**
     * 持有一个AtomicReference对象的Thread
     */
    public static class AtomicRefThread extends Thread{


        @Override
        public void run() {

            Integer value = atomicReference.get();
            Integer toUpdateValue = value + 1;
            if(value.equals(Integer.valueOf(2))){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            boolean updateRes = atomicReference.compareAndSet(value,toUpdateValue);
            System.out.println("update res " + updateRes + " value " + toUpdateValue.intValue());

        }

    }

}

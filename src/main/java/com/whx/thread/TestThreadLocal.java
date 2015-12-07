package com.whx.thread;

/**
 *
 * 正确的使用ThreadLocal的方式
 *      声明为将ThreadLocal声明为static ,加强生命周期,防止ThreadLocal弱引用被gc掉
 *      在线程执行完代码后threadlocal.remove,防止thread被复用时,entry数组内的单链表被重复使用,造成内存泄漏
 *      (并不会泄漏,原因是链表上同一个key的hash一样,并且key本身地址一样,会replace掉原来的对象key)
 *
 * Created by wanghongxing on 15/11/12.
 */
public class TestThreadLocal {

    private static final ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args){
        for(int i=0;i<5;i++){
            new Thread(new Task(i)).start();
        }
    }

    static class Task implements Runnable{

        private int index;

        public Task(int index) {
            this.index = index;
        }

        @Override
        public void run() {

            System.out.println("线程" + index + "的初始化value : " + integerThreadLocal.get());

            for(int i=0 ;i < 10;i++){
                integerThreadLocal.set(integerThreadLocal.get() + i);
            }

            System.out.println("线程" + index + "的累加value : " + integerThreadLocal.get());

        }
    }

}

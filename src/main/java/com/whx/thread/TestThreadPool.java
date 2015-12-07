package com.whx.thread;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 *
 * 事实证明线程池会保存执行过的线程
 * 所以线程池正确的试用方法,是初始化一次,被引用然后方法中post runnable到线程池中
 *
 * 即使可以设置线程销毁时间,但是这样做失去的使用线程池的意义
 *
 * Created by wanghongxing on 15/11/10.
 */
public class TestThreadPool {
    static ExecutorService threadPool = Executors.newFixedThreadPool(3);

    public static void runNewThreadPool(){

        Thread test = new Thread() {

            @Override
            public void run() {

                try {
                    Thread.currentThread().setName(String.valueOf(System.currentTimeMillis()));
                    Thread.sleep(1000 * 5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        test.start();

        System.out.println(test);

        threadPool.execute(test);

        threadPool.shutdown();

    }

    public static void runPrintThread(){
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        threadPool.execute(new Runnable() {
            @Override
            public void run() {

                while(true){
                    Map<Thread, StackTraceElement[]> maps = Thread.getAllStackTraces();
                    System.out.println(new Date() + " . thread num " + maps.entrySet().size());
                    StringBuilder sb = new StringBuilder();
                    for(Map.Entry entry : maps.entrySet()){
                        Thread thread = (Thread) entry.getKey();
                        sb.append(thread);
                    }
                    System.out.println(sb.toString());

                    try {
                        Thread.sleep(1000 * 10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
    }

    public static void main(String[] args){

        runPrintThread();

        for(int i = 0;i<3;i++){
            runNewThreadPool();
        }

        for(int i=0;i<1000;i++){
            Map map = new HashMap<>();
        }


        gc();

        try {
            Thread.sleep(1000*1000*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void gc() {

        while(true){
            System.gc();
            try {
                Thread.sleep(1000 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}

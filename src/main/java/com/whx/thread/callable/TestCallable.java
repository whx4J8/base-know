package com.whx.thread.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * 使用Fututre 可以异步的获取线程的结果
 *
 * Created by wanghongxing on 15/11/13.
 */
public class TestCallable {

    public static void main(String [] args){

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<Integer>> runResults = new ArrayList<>();
        for(int i=0;i<10;i++){
            runResults.add(executorService.submit(new TestCallable.Task(i)));
        }

        printResultAfterRun(runResults,Executors.newScheduledThreadPool(3));
    }

    public static void printResultAfterRun(List<Future<Integer>> futures,ScheduledExecutorService
            scheduledThreadPool ){

        scheduledThreadPool.scheduleAtFixedRate(() -> {
            futures.forEach(future -> {
                if (future.isDone()) {
                    try {
                        Integer num = future.get();
                        System.out.println("execute id " + num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("future is not complete");
                }
            });

        }, 0, 1, TimeUnit.SECONDS);

    }

    public static class Task implements Callable {

        private int id;

        public Task(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public Object call() throws Exception {

            if(id == 1 || id == 2){
                Thread.sleep(10 * 1000);
            }

            return this.getId();
        }

    }

}

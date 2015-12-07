package com.whx.thread.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * Timer 内部为单线程 任务队列模式
 *
 *      在跑定时时候,若是前一个任务一直被阻塞住,会影响后面的任务执行
 *      好的情况为延迟队列中任务的执行
 *      不好的情况丢失定时任务
 *
     Fri Nov 13 17:24:16 CST 2015 task 1 run
     Fri Nov 13 17:24:16 CST 2015 task 2 run
     Fri Nov 13 17:24:26 CST 2015 task 1 run
     Fri Nov 13 17:24:36 CST 2015 task 1 run
     Fri Nov 13 17:24:36 CST 2015 task 2 run
     Fri Nov 13 17:24:46 CST 2015 task 1 run
     Fri Nov 13 17:24:56 CST 2015 task 1 run
     Fri Nov 13 17:24:56 CST 2015 task 2 run
     Fri Nov 13 17:25:06 CST 2015 task 1 run
     Fri Nov 13 17:25:06 CST 2015 task 2 run
     Fri Nov 13 17:25:16 CST 2015 task 1 run
     Fri Nov 13 17:25:26 CST 2015 task 1 run
     Fri Nov 13 17:25:26 CST 2015 task 2 run
     Fri Nov 13 17:25:36 CST 2015 task 1 run
     Fri Nov 13 17:25:46 CST 2015 task 1 run
     Fri Nov 13 17:25:46 CST 2015 task 2 run
     Fri Nov 13 17:25:56 CST 2015 task 1 run
     Fri Nov 13 17:26:06 CST 2015 task 1 run
     Fri Nov 13 17:26:06 CST 2015 task 2 run
     Fri Nov 13 17:26:16 CST 2015 task 1 run
 *
 * Created by wanghongxing on 15/11/13.
 */
public class TestTimer {

    public static void main(String[] args){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new Date() + " task 1 run");
            }

        }, 1000, 2000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(new Date() + " task 2 run");
            }
        },1000,2000);


    }

}

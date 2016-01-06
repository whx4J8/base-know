package com.whx.thread;

/**
 * Created by wanghongxing on 15/12/24.
 */
public class TestSuperThread {

    public static class ChildThread extends Thread{

        public ChildThread() {
            super.start();
            try {
                Thread.sleep(1000 *2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ChildThread");
        }

        @Override
        public void run() {
            System.out.println("run");
        }
    }

    public static void main(String[] args){
        Thread thread = new ChildThread();
        thread.run();

    }


}

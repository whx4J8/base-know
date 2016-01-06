package com.whx.test;

/**
 * Created by wanghongxing on 15/12/7.
 */
public class Test {

    public static void whileFun(int value,RunReduce runReduce){
        for(int i=value;i>0;i--){
            runReduce.run(i);
        }
    }

    public static void main(String[] args){

        whileFun(10,num -> {
            System.out.println(num);
        });
    }


    public static interface RunReduce{
        void run(int num);
    }
}

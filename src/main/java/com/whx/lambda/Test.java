package com.whx.lambda;

/**
 *
 * lambada 在编译时被展开为静态方法,for 循环中的run3只被初始化一次
 *
 * Created by wanghongxing on 15/11/20.
 */
public class Test {


    public static void main(String[] args){

        Runnable run1 = () -> System.out.println();

        Runnable run2 = () -> System.out.println();

        System.out.println(run1.getClass());
        System.out.println(run2.getClass());

        for(int i=0;i<2;i++){

            Runnable run3 = () -> System.out.println();
            System.out.println(run3.getClass());

        }


    }

}

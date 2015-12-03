package com.whx.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * java 对cas操作的支持,带版本号
 *
 * Created by wanghongxing on 15/12/3.
 */
public class TestAtomicReference {

    public static AtomicReference<Integer> atomicReference = new AtomicReference<>();

    public static void main(String[] args){

        Integer integer = new Integer(1);
        atomicReference.set(integer);
        Integer updateInteger = new Integer(2);

        //一次cas操作,多线程环境下integer对象有可能在这里已经被改变,如果这样这次更新即为失败
        boolean updateRes = atomicReference.compareAndSet(integer,updateInteger);

        System.out.println(updateRes);
        System.out.println(atomicReference.get());

    }

}

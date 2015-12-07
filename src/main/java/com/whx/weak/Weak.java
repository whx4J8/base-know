package com.whx.weak;

import java.lang.ref.WeakReference;

/**
 * Created by wanghongxing on 15/11/12.
 */
public class Weak {

//    Object obj = new Object();
//    ReferenceQueue<Object> refQueue = new ReferenceQueue<Object>();
//    WeakReference<Object> weakRef = new WeakReference<Object>(obj, refQueue);
//    System.out.println(weakRef.get());
//    System.out.println(refQueue.poll());
//    obj = null;
//    System.gc();
//    System.out.println(weakRef.get());
//    System.out.println(refQueue.poll());

    public static void main(String[] args){

        Object obj = new Object();
        WeakReference<Object> weakRef = new WeakReference<Object>(obj);

        System.out.println(weakRef.get());

        obj = null;

        System.gc();

        System.out.println(weakRef.get());

    }

}

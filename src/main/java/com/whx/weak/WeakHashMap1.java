package com.whx.weak;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by wanghongxing on 15/11/11.
 */
public class WeakHashMap1 {

    static Map map = new WeakHashMap<>();

    public static void init(){
        Integer test1 = new Integer(1);
        Integer test2 = new Integer(2);

        map.put("1", test1);
        map.put("2", test2);

        test1 = null;
        test2 = null;
    }

    public static void print(){
        System.out.println(map.get("1"));
        System.out.println(map.get("2"));
    }

    public static void testWeak(){

        while(true){

            System.gc();

            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Integer key1 = (Integer) map.get("1");
            Integer key2 = (Integer) map.get("2");

            System.out.println(key1);
            System.out.println(key2);

            if(key1 == null||key2 == null){
                return;
            }

        }

    }

    public static void main(String[] args){

        init();
        print();
        testWeak();

    }


}

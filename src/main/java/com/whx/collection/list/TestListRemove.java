package com.whx.collection.list;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 在便利过程中,list不能对原来容器进行增删改,否则会报出java.util.ConcurrentModificationException
 *
 * Created by wanghongxing on 15/12/15.
 */
public class TestListRemove {

    public static void main(String[] args){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(i);
        }

        for(Integer integer : list){
            System.out.println(integer);
            list.remove(integer);//this exception
        }

    }

}

package com.whx.collection.list;

import java.util.ArrayList;
import java.util.List;

/**
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
            list.remove(integer);
        }

    }

}

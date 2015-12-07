package com.whx.HashMap;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * hash map
 *
 *      -->hashmap 存放一个油hash组成的数组,每个item上有一个linkedlist,存放hash值相同的key,
 *                  若时hashcode不同,将存放到不同的hash的链表上
 *                  hashcode相同时,使用equals方法判断key是否相同
 *                                  --- equals方法相同, 替换原来key在链表上的位置
 *                                  --- equals方法不同,添加到链表上
 *
 * Created by wanghongxing on 15/11/12.
 */
public class TestHashMap {

    public static void main(String[] args){

        Map<Demo,String> map = new HashMap<>();
        Demo demo1 = new Demo("name1","pass1");
        Demo demo2 = new Demo("name2","pass2");

        map.put(demo2, "value2");
        map.put(demo1, "value1");

        System.out.println(map.get(demo1));

        Long num = null;
        Integer num1 = null;
    }


    public static class Demo{

        private String name;
        private String password;

        public Demo(String name, String password) {
            this.name = name;
            this.password = password;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Demo demo = (Demo) o;

            if (name != null ? !name.equals(demo.name) : demo.name != null) return false;
            return !(password != null ? !password.equals(demo.password) : demo.password != null);

        }

        @Override
        public int hashCode() {
            return 10;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}

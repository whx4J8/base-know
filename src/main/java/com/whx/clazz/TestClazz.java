package com.whx.clazz;

/**
 * Created by wanghongxing on 15/11/23.
 */
public class TestClazz {

    public static void main(String[] args){

        Father father = new Child();
        /**
         * 变量随着对象孩父节点存在
         * this.getClass()只会指向当前子类的Class
         */
        father.print();

    }
}


class Child extends Father{

    int i = 1;
}

class Father{

    int i = 0;

    public void print(){
        System.out.println(this.i);
        System.out.println(this.getClass());
    }

}
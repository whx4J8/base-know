package com.whx.transaction;

/**
 * 事务本身并不能扛住并发
 *
 * 线程1      查询库存    更新      提交事务
 * 线程2         查询库存      更新           提交事务
 *
 * 数据库某些字段需要进行同步
 *      1.zk同步锁
 *      2.redis同步
 *      3.
 *
 * 所以可以使用redis排他锁实现同步    (当然这样做不是做好的方案)
 *
 * Created by wanghongxing on 15/11/15.
 */
public class TestTransaction {

    public static void main(String[] args){

    }

}

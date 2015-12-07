package com.whx.redis;

/**
 *
 * jedis一直爆出can't get resource from pool
 * 后来查明原因redis-server在内存为服务器内存的50%上时,不能分配内存
 *
 * Can't save in background: fork: Cannot allocate memory
 * 可以进行修改redis上限内存配置,并配置持久化db策略
 *
 * Created by wanghongxing on 15/11/24.
 */
public class TestRedis {

}

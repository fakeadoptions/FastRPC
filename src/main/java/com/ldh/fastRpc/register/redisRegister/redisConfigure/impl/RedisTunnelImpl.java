package com.ldh.fastRpc.register.redisRegister.redisConfigure.impl;

import com.alibaba.fastjson.JSON;
import com.ldh.fastRpc.register.ServiceMeta;
import com.ldh.fastRpc.register.redisRegister.redisConfigure.RedisTunnel;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @program: FastRPC
 * @description: redis防腐层实现类
 * @author: Donghui Li
 * @create: 2021-11-16 21:16
 */
@Component
public class RedisTunnelImpl implements RedisTunnel {

    static Jedis jedis = new Jedis("localhost", 6379);

    public boolean setKey(String key, ServiceMeta value) {
        jedis.set(key, JSON.toJSONString(value));
        return jedis.exists(key) ? true : false;
    }

    public ServiceMeta getKey(String key) {
        String value = jedis.get(key);
        ServiceMeta serviceMeta = JSON.parseObject(value, ServiceMeta.class);
        return serviceMeta;
    }
}

package com.ldh.fastRpc.register.redisRegister;

import com.ldh.fastRpc.register.Register;
import com.ldh.fastRpc.register.ServiceMeta;
import com.ldh.fastRpc.register.redisRegister.redisConfigure.RedisTunnel;
import com.ldh.fastRpc.register.redisRegister.redisConfigure.impl.RedisTunnelImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @program: FastRPC
 * @description: redis实现注册中心
 * @author: Donghui Li
 * @create: 2021-11-16 19:38
 */
@Component
public class RedisRegister implements Register {

    @Autowired
    RedisTunnel redisTunnel;

    public boolean registerService(ServiceMeta serviceMeta) {
        return redisTunnel.setKey(serviceMeta.getFullyQualifiedName(),serviceMeta);
    }

    public ServiceMeta getService(String fullyQualifiedName) {
        return redisTunnel.getKey(fullyQualifiedName);
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("name","ldh");
        String name = jedis.get("name");
        System.out.println(name);
    }
}

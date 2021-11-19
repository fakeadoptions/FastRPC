package com.ldh.fastRpc.register.redisRegister.redisConfigure;

import com.ldh.fastRpc.register.ServiceMeta;

/**
 * @program: FastRPC
 * @description: redis防腐层
 * @author: Donghui Li
 * @create: 2021-11-16 21:10
 */
public interface RedisTunnel {

    boolean setKey(String key, ServiceMeta value);

    ServiceMeta getKey(String key);

}

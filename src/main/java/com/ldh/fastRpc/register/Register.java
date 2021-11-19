package com.ldh.fastRpc.register;

/**
 * @program: FastRPC
 * @description: 通过实现该接口可以完成注册中心的使用
 * @author: Donghui Li
 * @create: 2021-11-15 10:29
 */

public interface Register {

    boolean registerService(ServiceMeta serviceMeta);

    ServiceMeta getService(String fullyQualifiedName);

}

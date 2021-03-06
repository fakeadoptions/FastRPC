package com.ldh.fastRpc.server;

import java.net.UnknownHostException;

/**
 * @program: FastRPC
 * @description: 服务提供方通过该接口发布服务
 * @author: Donghui Li
 * @create: 2021-11-15 10:28
 */
public interface FastRpcProvider {
    
    boolean publishService(Class serviceInterface,Class serviceInterfaceImpl,String fullyQualifiedName);
    
}

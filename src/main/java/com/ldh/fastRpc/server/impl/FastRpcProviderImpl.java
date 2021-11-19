package com.ldh.fastRpc.server.impl;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.ldh.fastRpc.netIO.server.NettyServer;
import com.ldh.fastRpc.register.Register;
import com.ldh.fastRpc.register.ServiceMeta;
import com.ldh.fastRpc.server.FastRpcProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: FastRPC
 * @description: 服务发布实现类
 * @author: Donghui Li
 * @create: 2021-11-15 11:05
 */
@Component
public class FastRpcProviderImpl implements FastRpcProvider {

    @Autowired
    Register register;

    private static final String port = "10086";

    public boolean publishService(Class serviceInterface,Class serviceInterfaceImpl,String fullyQualifiedName) {
        ServiceMeta serviceMeta = ServiceMeta.builder()
                .ipAddress(getIp())
                .fullyQualifiedName(fullyQualifiedName)
                .port(port)
                .build();
        register.registerService(serviceMeta);
        new NettyServer(10086);
        return true;
    }

    /**
     * 获取本机ip
     * @return String
     */
    private String getIp(){
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostAddress();
        }catch (Exception e){
            System.out.println("get ip is error");
        }
        return "127.0.0.1";
    }


}

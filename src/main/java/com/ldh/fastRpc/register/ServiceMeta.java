package com.ldh.fastRpc.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: FastRPC
 * @description: 服务元信息
 * @author: Donghui Li
 * @create: 2021-11-15 10:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceMeta {

    /**
     * ip地址
     */
    String ipAddress;

    /**
     * 端口地址
     */
    String port;

    /**
     * 全限定名
     */
    String fullyQualifiedName;
}

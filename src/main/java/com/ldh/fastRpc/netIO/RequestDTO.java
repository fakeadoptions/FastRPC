package com.ldh.fastRpc.netIO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: FastRPC
 * @description: 发送请求的单元
 * @author: Donghui Li
 * @create: 2021-11-19 16:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDTO {

    /**
     * 全限定名
     */
    String fullyQualifiedName;

    /**
     * 方法名
     */
    String methodName;

    /**
     * 参数类型，为了避免有方法重载时不知道选择哪个方法
     */
    Class<?>[] parameterTypes;

    /**
     * 入参数
     */
    Object[] param;

    /**
     * 反参数类型
     */
    Class<?> returnTypes;

}

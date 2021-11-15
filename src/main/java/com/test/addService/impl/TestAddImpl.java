package com.test.addService.impl;

import com.test.addService.TestAdd;

/**
 * @program: FastRPC
 * @description: 提供加法服务
 * @author: Donghui Li
 * @create: 2021-11-15 10:56
 */
public class TestAddImpl implements TestAdd {
    public int add(int a, int b) {
        return a + b;
    }
}

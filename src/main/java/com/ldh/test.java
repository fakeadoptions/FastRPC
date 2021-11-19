package com.ldh;
import com.ldh.fastRpc.server.FastRpcProvider;
import com.ldh.fastRpc.test.addService.TestAdd;
import com.ldh.fastRpc.test.addService.impl.TestAddImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: FastRPC
 * @description: test
 * @author: Donghui Li
 * @create: 2021-11-15 20:25
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.ldh.fastRpc"})
public class test implements CommandLineRunner {

    @Autowired
    FastRpcProvider fastRpcProvider;

    public static void main(String[] args) {
        SpringApplication.run(test.class, args);
    }

    public void run(String... args) throws Exception {
        fastRpcProvider.publishService(TestAdd.class,TestAddImpl.class,"com.ldh.fastRpc.test.addService.impl.TestAddImpl");
    }
}

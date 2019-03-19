package com.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient //实现一个Eureka客户端  激活一个DiscoveryClient 实例
@SpringBootApplication
public class EurekaClientProvideApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientProvideApplication.class, args);
    }

}

package com.ribbon.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient //实现一个Eureka客户端  激活一个DiscoveryClient 实例
@SpringBootApplication
public class RibbonConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonConsumerApplication.class, args);
    }

    /**
     * 创建负载均衡客户端
     * @return
     */
    @LoadBalanced //开启负载均衡策略
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

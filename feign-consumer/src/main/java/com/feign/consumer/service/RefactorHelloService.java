package com.feign.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("hello-service") //服务名不区分大小写
public interface RefactorHelloService extends service.HelloService {
}

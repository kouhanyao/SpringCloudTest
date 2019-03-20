package com.eureka.client.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * 可以看到通过继承的形式，在controller中不在包含以往定义的请求映射注解@RequestMapping，而参数的注解定义在重写的时候
 * 会自动带过来
 */
@RestController
public class RefactorHelloController implements service.HelloService {
    @Override
    public String hello(String name) {
        return "hello " + name;
    }

    @Override
    public dto.User hello(String name, Integer age) {
        return new dto.User(name, age);
    }

    @Override
    public String hello(dto.User user) {
        return "hello " + user.getName() + "， " + user.getAge();
    }
}

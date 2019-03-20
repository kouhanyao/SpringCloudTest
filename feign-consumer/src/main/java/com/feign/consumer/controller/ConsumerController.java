package com.feign.consumer.controller;

import com.feign.consumer.entity.User;
import com.feign.consumer.service.HelloService;
import com.feign.consumer.service.RefactorHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    HelloService helloService;

    @Autowired
    RefactorHelloService refactorHelloService;

    @RequestMapping(value = "/feign-consumer", method = RequestMethod.GET)
    public String helloConsumer() {
        return helloService.hello();
    }

    @RequestMapping(value = "/feign-consumer2", method = RequestMethod.GET)
    public String helloConsumer2() {
        StringBuilder sb = new StringBuilder();
        sb.append(helloService.hello()).append("\n");
        sb.append(helloService.hello1("DIDI")).append("\n");
        sb.append(helloService.hello2("DIDI", 20)).append("\n");
        sb.append(helloService.hello3(new User("DIDI", 30))).append("\n");
        System.out.println(sb.toString());
        return sb.toString();
    }

    @RequestMapping(value = "/feign-consumer3", method = RequestMethod.GET)
    public String helloConsumer3() {
        StringBuilder sb = new StringBuilder();
        sb.append(refactorHelloService.hello("MIMI")).append("\n");
        sb.append(refactorHelloService.hello("MIMI", 200)).append("\n");
        sb.append(refactorHelloService.hello(new dto.User("MIMI", 300))).append("\n");
        System.out.println(sb.toString());
        return sb.toString();
    }
}

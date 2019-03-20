package com.eureka.client.controller;

import com.eureka.client.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        List<String> services = client.getServices();

        services.stream().forEach(x -> {
            System.out.println("services: " + x);
            List<ServiceInstance> serviceInstances = client.getInstances(x);
            serviceInstances.stream().forEach(y ->
                    {
                        System.out.println("host: " + y.getHost());
                        System.out.println("instanedId: " + y.getInstanceId());
                        System.out.println("Scheme: " + y.getScheme());
                        System.out.println("ServiceId: " + y.getServiceId());
                        System.out.println("Port: " + y.getPort());
                        System.out.println("uri: " + y.getUri());
                    }
            );
        });
        //System.out.println("test");
        return "Hello worldf";
    }

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello1(@RequestParam String name) {
        return "hello " + name;
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public User hello2(@RequestHeader String name, @RequestHeader Integer age) {
        return new User(name, age);
    }

    @RequestMapping(value = "/hello3", method = RequestMethod.POST)
    public String hello3(@RequestBody User user) {
        return "hello " + user.getName() + ", " + user.getAge();
    }
}

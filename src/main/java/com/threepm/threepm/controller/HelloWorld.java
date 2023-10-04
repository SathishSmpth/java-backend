package com.threepm.threepm.controller;

import org.springframework.web.bind.annotation.*;
import com.threepm.threepm.payload.HelloWorldBean;


@RestController
public class HelloWorld {

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Welcome to Spring boot";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Welcome to java world");
    }

    @GetMapping(path = "/hello-world-bean/{id}")
    public int pathVar(@PathVariable int id) {
        return id;
    }
}

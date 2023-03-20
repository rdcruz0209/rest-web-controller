package com.portfolioprojects.rest.webservices.restfulwebservices.helloworld;

import jakarta.persistence.Column;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//expose a rest api
@RestController
public class HelloWorldController {
//    give specific url to rest-api
//    return "Hello World"


    @GetMapping("hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("HelloWorld");
    }

    @GetMapping("hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(
                String.format("%s%s", "HelloWorld ", name));
    }

}

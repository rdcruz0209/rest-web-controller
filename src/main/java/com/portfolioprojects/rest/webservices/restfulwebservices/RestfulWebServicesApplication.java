package com.portfolioprojects.rest.webservices.restfulwebservices;

import com.portfolioprojects.rest.webservices.restfulwebservices.user.UserResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RestfulWebServicesApplication {

    private ConfigurableApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(RestfulWebServicesApplication.class, args);
    }


}

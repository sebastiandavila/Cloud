package com.example.backlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BacklogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BacklogApplication.class, args);
    }

}

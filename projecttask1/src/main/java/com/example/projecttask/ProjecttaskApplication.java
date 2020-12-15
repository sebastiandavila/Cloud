package com.example.projecttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProjecttaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjecttaskApplication.class, args);
    }

}

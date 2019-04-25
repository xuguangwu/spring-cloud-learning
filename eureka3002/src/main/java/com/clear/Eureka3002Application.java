package com.clear;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Eureka3002Application {

    public static void main(String[] args) {
        SpringApplication.run(Eureka3002Application.class);
    }

}

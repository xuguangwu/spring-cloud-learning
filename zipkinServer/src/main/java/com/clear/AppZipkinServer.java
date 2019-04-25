package com.clear;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.internal.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
@EnableEurekaClient
public class AppZipkinServer {
    public static void main(String[] args) {
        SpringApplication.run(AppZipkinServer.class);
    }
}
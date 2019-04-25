package com.clear;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.internal.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
public class AppSleuth {
    public static void main(String[] args) {
        SpringApplication.run(AppSleuth.class);
    }
}
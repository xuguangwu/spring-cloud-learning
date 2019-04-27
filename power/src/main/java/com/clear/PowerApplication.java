package com.clear;

import com.clear.entity.Entity;
import com.clear.service.CityESService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableEurekaClient
public class PowerApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(PowerApplication.class);
		CityESService cityESService = applicationContext.getBean(CityESService.class);
		Entity entity = new Entity();
		entity.setId(1L);
		entity.setName("1");
		cityESService.saveEntity(entity);
    }

}

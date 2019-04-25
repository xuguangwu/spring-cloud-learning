package com.clear.controller;


import com.clear.R;
import com.clear.service.PowerFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping
public class UserController {

    @Resource
    private RestTemplate restTemplate;

    private final static String POWER_URL = "http://SERVER-POWER";

    @Resource
    private PowerFeignClient powerFeignClient;

    @RequestMapping("feign")
//    @HystrixCommand(fallbackMethod = "userFallback")
    public R getFeignUser() {
        return powerFeignClient.getUser();
    }


    @RequestMapping("user")
    public R getUser() {
        return restTemplate.getForObject(POWER_URL + "/user", R.class);
    }

    public R userFallback() {
        return R.error("失败了");
    }


}

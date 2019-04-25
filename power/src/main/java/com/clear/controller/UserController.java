package com.clear.controller;

import com.clear.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    @GetMapping("/user")
    public R getUserInfo() {
        throw new RuntimeException();
//        return R.success();
    }

    @GetMapping("/power")
    public R getPower() {
        return R.success();
    }
}

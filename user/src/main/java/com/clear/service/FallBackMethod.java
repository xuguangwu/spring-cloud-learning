package com.clear.service;

import com.clear.R;
import org.springframework.stereotype.Component;

@Component
public class FallBackMethod implements PowerFeignClient {

    @Override
    public R getUser() {
        return R.error("getUser fail");
    }
}

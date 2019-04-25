package com.clear.service;

import com.clear.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author xuguangwu
 */
@FeignClient(value = "SERVER-POWER", fallback = FallBackMethod.class)
public interface PowerFeignClient {

    @RequestMapping("user")
    R getUser();

}

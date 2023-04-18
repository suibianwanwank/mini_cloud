package com.ccsu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Component
@FeignClient(value = "mycloud2-user")
public interface FeignUserService {
    @RequestMapping("user")
    public void user();
}

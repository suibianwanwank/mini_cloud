package com.ccsu.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    // 熔断与降级处理
    @SentinelResource(value = "doSomeThing2", fallback = "fallbackHandler")
    public void doSomeThing2(String str) {
        System.out.println("不对劲");

//        throw new RuntimeException("发生异常");
    }

    public void fallbackHandler(String str) {
        System.out.println("纯纯的不对劲");
    }
}

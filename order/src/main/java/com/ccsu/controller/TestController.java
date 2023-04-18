package com.ccsu.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ccsu.feign.FeignUserService;
import com.ccsu.pojo.Ware;
import com.ccsu.service.TestService;
import com.ccsu.service.WareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    public static final String RESOURCE_NAME_QUERY_USER_BY_NAME = "suibianwanwanTest";

    @Autowired
    TestService testService;

    @Resource
    FeignUserService feignUserService;




    @RequestMapping("/test")
    public String suibianwanwanTest(){

        feignUserService.user();
        testService.doSomeThing2("sda");
        System.out.println("sdsad");
        return "sdad";
    }


    @Resource
    WareService wareService;
    @RequestMapping("ware/{id}")
    public Ware getware(@PathVariable Integer id){
        System.out.println("ware方法");
        Ware ware = wareService.getWare(id);
        System.out.println(ware);
        return ware;
    }



}

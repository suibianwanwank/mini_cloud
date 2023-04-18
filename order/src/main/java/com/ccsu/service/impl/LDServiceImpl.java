package com.ccsu.service.impl;

import com.ccsu.service.LDService;

public class LDServiceImpl implements LDService {


    @Override
    public void secKill() {
        System.out.println("秒杀业务");

        System.out.println("处理订单");

        System.out.println("将消息加入消息队列");

        System.out.println("结束业务");

    }
}

package com.ccsu;

import com.ccsu.config.RabbitmqConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class OrderApplicationTests {
    @Resource
    RabbitTemplate rabbitTemplate;
    @Test
    void contextLoads() {
        String q="dsa";
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS,RabbitmqConfig.ROUTING_KEY_UPDATE,q);
    }

}

package com.ccsu.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    public static final String QUEUE_DATABASE_UPDATE = "queue_database_update";
    public static final String EXCHANGE_TOPICS= "amq.topic";
    public static final String ROUTING_KEY_UPDATE= "topic_key_database_update";


    //声明交换机
    @Bean(EXCHANGE_TOPICS)
    public Exchange exchangeTopic() {
        //durable(true) 持久化，mq重启之后交换机还在
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS).durable(true).build();
    }

    //声明QUEUE_INFORM_EMAIL队列
    @Bean(QUEUE_DATABASE_UPDATE)
    public Queue queueDataBaseUpdate() {
        return new Queue(QUEUE_DATABASE_UPDATE);
    }

    //声明QUEUE_INFORM_SMS队列


    //ROUTINGKEY_EMAIL队列绑定交换机，指定routingKey
    @Bean
    public Binding BINDING_QUEUE_INFORM_EMAIL(@Qualifier(QUEUE_DATABASE_UPDATE) Queue queue,
                                              @Qualifier(EXCHANGE_TOPICS) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_UPDATE).noargs();
    }

    //ROUTINGKEY_SMS队列绑定交换机，指定routingKey



}

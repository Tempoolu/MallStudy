package com.example.demo.simple;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleRabbitConfig {

    // 简单模式：包含一个生产者，一个消费者，一个队列。生产者向队列发送消息，消费者从队列中获得消息并消费
    @Bean
    public Queue hello() {
        return new Queue("simple.hello");
    }

    @Bean
    public SimpleSender simpleSender() {
        return new SimpleSender();
    }

    @Bean
    public SimpleReceiver simpleReceiver() {
        return new SimpleReceiver();
    }
}

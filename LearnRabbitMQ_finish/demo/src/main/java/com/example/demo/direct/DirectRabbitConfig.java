package com.example.demo.direct;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitConfig {
    // 路由模式：由路由器选择给多个消费者发送消息的模式。包括一个生产者，两个消费者，两个队列，一个交换机。
    // 两个消费者同时绑定到不同的队列上，两个队列通过路由键绑定到交换机。
    // 生产者发送消息到交换机，交换机通过路由键转发到不同队列，队列绑定的消费者接收并消费信息。

    @Bean
    public DirectExchange direct() {
        return new DirectExchange("exchange.direct");
    }

    @Bean
    public Queue directQueue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue directQueue2() {
        return new AnonymousQueue();
    }

    // 将路由和队列绑定，并确认routing key路由键
    // 队列1绑定orange
    @Bean
    public Binding directBinding1a(DirectExchange direct, Queue directQueue1) {
        return BindingBuilder.bind(directQueue1).to(direct).with("orange");
    }

    @Bean
    public Binding directBinding2a(DirectExchange direct, Queue directQueue2) {
        return BindingBuilder.bind(directQueue2).to(direct).with("green");
    }

    @Bean
    public Binding directBinding2b(DirectExchange direct, Queue directQueue2) {
        return BindingBuilder.bind(directQueue2).to(direct).with("black");
    }

    @Bean
    public DirectReceiver receiver() {
        return new DirectReceiver();
    }

    @Bean
    public DirectSender sender() {
        return new DirectSender();
    }













}

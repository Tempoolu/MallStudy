package com.example.demo.topic;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {

    // 主题模式（通配符模式）：根据路由键匹配规则选择性给多个消费者发送消息的模式。包含一个生产者，两个消费者，两个队列，一个交换机。
    // 两个消费者同时绑定到不同队列上，两个队列通过路由键匹配规则绑定到交换机，生产者发送消息到交换机。
    // 交换机通过路由匹配规则转发到不同队列，队列绑定的消费者接收并消费信息。
    @Bean
    public TopicExchange topic() {
        return new TopicExchange("exchange.topic");
    }

    @Bean
    public Queue topicQueue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue topicQueue2() {
        return new AnonymousQueue();
    }

    // * 只能匹配一个单词
    // # 可以匹配零个或多个单词
    @Bean
    public Binding topicBinding1a(TopicExchange topic, Queue topicQueue1) {
        return BindingBuilder.bind(topicQueue1).to(topic).with("*.orange.*");
    }

    @Bean
    public Binding topicBinding2a(TopicExchange topic, Queue topicQueue2) {
        return BindingBuilder.bind(topicQueue2).to(topic).with("*.*.rabbit");
    }

    @Bean
    public Binding topicBinding2b(TopicExchange topic, Queue topicQueue2) {
        return BindingBuilder.bind(topicQueue2).to(topic).with("lazy.#");
    }

    @Bean
    public TopicReceiver topicReceiver() {
        return new TopicReceiver();
    }

    @Bean
    public TopicSender topicSender() {
        return new TopicSender();
    }
}

package com.example.demo.direct;

import com.example.demo.fanout.FanoutSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class DirectSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(FanoutSender.class);

    @Autowired
    private RabbitTemplate template;

    private static final String exchangeName = "exchange.direct";

    private final String[] keys = {"orange", "green", "black"};

    public void send(int index) {
        StringBuilder builder = new StringBuilder("Hello to ");
        int limitIndex = index%3;
        String key = keys[limitIndex];
        builder.append(key).append(' ').append(index+1);
        for (int i=0; i<limitIndex; i++) {
            builder.append('.');
        }
        String message = builder.toString();
        template.convertAndSend(exchangeName, key, message);
        LOGGER.info("[x] sent '{}'", message);
    }


}

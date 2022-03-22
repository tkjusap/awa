package com.funtap.awass.rabbitmq.configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

@Configuration
public class Base64EncodeSecretConfiguration {
    public static final String EXCHANGE_Base64 = "base64-exchange";
    public static final String QUEUE_Base64 = "base64-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_Base64)
    Queue queue() {
        return new Queue(QUEUE_Base64, false);
    }

    @Bean(name = EXCHANGE_Base64)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_Base64);
    }

    @Bean(name = "bindingbase64")
    Binding binding(@Qualifier(QUEUE_Base64)Queue queue,@Qualifier(EXCHANGE_Base64) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

package com.funtap.awass.rabbitmq.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.CountDownLatch;

public class PasswordAttacksConfiguration {
    public static final String EXCHANGE_PasswordAttacks = "PasswordAttacks-exchange";
    public static final String QUEUE_PasswordAttacks = "PasswordAttacks-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_PasswordAttacks)
    Queue queue() {
        return new Queue(QUEUE_PasswordAttacks, false);
    }

    @Bean(name = EXCHANGE_PasswordAttacks)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_PasswordAttacks);
    }

    @Bean(name = "PasswordAttacks")
    Binding binding(@Qualifier(QUEUE_PasswordAttacks)Queue queue, @Qualifier(EXCHANGE_PasswordAttacks) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

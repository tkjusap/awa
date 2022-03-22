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
public class WeakPasswordsConfiguration {
    public static final String EXCHANGE_weakPasswords = "weakPasswords-exchange";
    public static final String QUEUE_weakPasswords = "weakPasswords-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_weakPasswords)
    Queue queue() {
        return new Queue(QUEUE_weakPasswords, false);
    }

    @Bean(name = EXCHANGE_weakPasswords)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_weakPasswords);
    }

    @Bean(name = "PasswordAttacks")
    Binding binding(@Qualifier(QUEUE_weakPasswords)Queue queue, @Qualifier(EXCHANGE_weakPasswords) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

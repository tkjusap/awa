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
public class SQLiRabbitConfiguration {
    public static final String EXCHANGE_SQLi = "sqli-exchange";
    public static final String QUEUE_SQLi = "sqli-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_SQLi)
    Queue queue() {
        return new Queue(QUEUE_SQLi, false);
    }

    @Bean(name = EXCHANGE_SQLi)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_SQLi);
    }

    @Bean(name = "bindingsqli")
    Binding binding(@Qualifier(QUEUE_SQLi)Queue queue,@Qualifier(EXCHANGE_SQLi) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

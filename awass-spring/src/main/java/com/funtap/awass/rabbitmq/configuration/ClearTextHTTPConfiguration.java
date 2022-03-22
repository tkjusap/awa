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
public class ClearTextHTTPConfiguration {
    public static final String EXCHANGE_Clear = "clear-exchange";
    public static final String QUEUE_Clear = "clear-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_Clear)
    Queue queue() {
        return new Queue(QUEUE_Clear, false);
    }

    @Bean(name = EXCHANGE_Clear)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_Clear);
    }

    @Bean(name = "bindingclear")
    Binding binding(@Qualifier(QUEUE_Clear)Queue queue,@Qualifier(EXCHANGE_Clear) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

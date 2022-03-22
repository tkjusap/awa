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
public class InsecureDeserializationConfiguration {
    public static final String EXCHANGE_ID = "id-exchange";
    public static final String QUEUE_ID = "id-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_ID)
    Queue queue() {
        return new Queue(QUEUE_ID, false);
    }

    @Bean(name = EXCHANGE_ID)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_ID);
    }

    @Bean(name = "bindingid")
    Binding binding(@Qualifier(QUEUE_ID)Queue queue,@Qualifier(EXCHANGE_ID) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

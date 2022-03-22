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
public class AdministrativePortalsConfiguration {
    public static final String EXCHANGE_Admin = "admin-exchange";
    public static final String QUEUE_Admin = "admin-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_Admin)
    Queue queue() {
        return new Queue(QUEUE_Admin, false);
    }

    @Bean(name = EXCHANGE_Admin)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_Admin);
    }

    @Bean(name = "bindingadmin")
    Binding binding(@Qualifier(QUEUE_Admin)Queue queue,@Qualifier(EXCHANGE_Admin) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

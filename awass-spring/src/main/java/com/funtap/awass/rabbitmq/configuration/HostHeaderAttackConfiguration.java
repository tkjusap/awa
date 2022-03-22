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
public class HostHeaderAttackConfiguration {
    public static final String EXCHANGE_Host = "host-exchange";
    public static final String QUEUE_Host = "host-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_Host)
    Queue queue() {
        return new Queue(QUEUE_Host, false);
    }

    @Bean(name = EXCHANGE_Host)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_Host);
    }

    @Bean(name = "bindinghost")
    Binding binding(@Qualifier(QUEUE_Host)Queue queue,@Qualifier(EXCHANGE_Host) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

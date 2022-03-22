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
public class sensitivefileConfiguration {
    public static final String EXCHANGE_Sensi = "sensi-exchange";
    public static final String QUEUE_Sensi = "sensi-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_Sensi)
    Queue queue() {
        return new Queue(QUEUE_Sensi, false);
    }

    @Bean(name = EXCHANGE_Sensi)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_Sensi);
    }

    @Bean(name = "bindingsensi")
    Binding binding(@Qualifier(QUEUE_Sensi)Queue queue,@Qualifier(EXCHANGE_Sensi) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

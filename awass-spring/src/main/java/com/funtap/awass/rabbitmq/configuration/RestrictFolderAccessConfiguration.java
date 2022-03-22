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
public class RestrictFolderAccessConfiguration {
    public static final String EXCHANGE_Res = "res-exchange";
    public static final String QUEUE_Res = "res-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_Res)
    Queue queue() {
        return new Queue(QUEUE_Res, false);
    }

    @Bean(name = EXCHANGE_Res)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_Res);
    }

    @Bean(name = "bindingres")
    Binding binding(@Qualifier(QUEUE_Res)Queue queue,@Qualifier(EXCHANGE_Res) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

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
public class DirectoryTraversalFilesRabbitConfiguration {
    public static final String EXCHANGE_DTF = "DTF-exchange";
    public static final String QUEUE_DTF = "DTF-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_DTF)
    Queue queue() {
        return new Queue(QUEUE_DTF, false);
    }

    @Bean(name = EXCHANGE_DTF)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_DTF);
    }

    @Bean(name = "bindingdtf")
    Binding binding(@Qualifier(QUEUE_DTF)Queue queue,@Qualifier(EXCHANGE_DTF) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

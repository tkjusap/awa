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
public class InsecureLoginFormsConfiguration {
    public static final String EXCHANGE_IL = "il-exchange";
    public static final String QUEUE_IL = "il-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_IL)
    Queue queue() {
        return new Queue(QUEUE_IL, false);
    }

    @Bean(name = EXCHANGE_IL)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_IL);
    }

    @Bean(name = "bindingil")
    Binding binding(@Qualifier(QUEUE_IL)Queue queue,@Qualifier(EXCHANGE_IL) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

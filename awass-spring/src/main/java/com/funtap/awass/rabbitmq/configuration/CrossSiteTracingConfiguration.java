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
public class CrossSiteTracingConfiguration {
    public static final String EXCHANGE_XST = "xst-exchange";
    public static final String QUEUE_XST = "xst-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_XST)
    Queue queue() {
        return new Queue(QUEUE_XST, false);
    }

    @Bean(name = EXCHANGE_XST)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_XST);
    }

    @Bean(name = "bindingxst")
    Binding binding(@Qualifier(QUEUE_XST)Queue queue,@Qualifier(EXCHANGE_XST) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

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
public class SSIinjectionConfiguration {
    public static final String EXCHANGE_SSIi = "ssii-exchange";
    public static final String QUEUE_SSIi = "ssii-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_SSIi)
    Queue queue() {
        return new Queue(QUEUE_SSIi, false);
    }

    @Bean(name = EXCHANGE_SSIi)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_SSIi);
    }

    @Bean(name = "bindingssii")
    Binding binding(@Qualifier(QUEUE_SSIi)Queue queue, @Qualifier(EXCHANGE_SSIi) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

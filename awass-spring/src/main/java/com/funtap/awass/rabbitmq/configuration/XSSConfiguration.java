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
public class XSSConfiguration {
    public static final String EXCHANGE_XSS = "xss-exchange";
    public static final String QUEUE_XSS = "xss-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_XSS)
    Queue queue() {
        return new Queue(QUEUE_XSS, false);
    }

    @Bean(name = EXCHANGE_XSS)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_XSS);
    }

    @Bean(name = "bindingxss")
    Binding binding(@Qualifier(QUEUE_XSS)Queue queue, @Qualifier(EXCHANGE_XSS) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

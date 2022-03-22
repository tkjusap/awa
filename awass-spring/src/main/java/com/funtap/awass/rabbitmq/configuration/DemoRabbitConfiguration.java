package com.funtap.awass.rabbitmq.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

@Configuration
@EnableRabbit
public class DemoRabbitConfiguration {
    public static final String EXCHANGE_HELLO = "hello-exchange";
    public static final String QUEUE_HELLO = "hello-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_HELLO)
    Queue queue() {
        return new Queue(QUEUE_HELLO, false);
    }

    @Bean(name = EXCHANGE_HELLO)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_HELLO);
    }

    @Bean(name = "bindinghello")
    Binding binding(@Qualifier(QUEUE_HELLO)Queue queue,@Qualifier(EXCHANGE_HELLO) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

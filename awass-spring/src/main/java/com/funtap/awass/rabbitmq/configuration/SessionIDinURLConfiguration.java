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
public class SessionIDinURLConfiguration {
    public static final String EXCHANGE_Ss = "ss-exchange";
    public static final String QUEUE_Ss = "ss-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_Ss)
    Queue queue() {
        return new Queue(QUEUE_Ss, false);
    }

    @Bean(name = EXCHANGE_Ss)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_Ss);
    }

    @Bean(name = "bindingss")
    Binding binding(@Qualifier(QUEUE_Ss)Queue queue,@Qualifier(EXCHANGE_Ss) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

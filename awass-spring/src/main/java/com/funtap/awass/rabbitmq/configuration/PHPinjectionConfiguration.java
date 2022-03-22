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
public class PHPinjectionConfiguration {
    public static final String EXCHANGE_PHPi = "phpi-exchange";
    public static final String QUEUE_PHPi = "phpi-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_PHPi)
    Queue queue() {
        return new Queue(QUEUE_PHPi, false);
    }

    @Bean(name = EXCHANGE_PHPi)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_PHPi);
    }

    @Bean(name = "bindingphpi")
    Binding binding(@Qualifier(QUEUE_PHPi)Queue queue, @Qualifier(EXCHANGE_PHPi) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

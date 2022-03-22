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
public class PHPCGIRCEConfiguration {
    public static final String EXCHANGE_PHPrce = "phprce-exchange";
    public static final String QUEUE_PHPrce = "phprce-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_PHPrce)
    Queue queue() {
        return new Queue(QUEUE_PHPrce, false);
    }

    @Bean(name = EXCHANGE_PHPrce)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_PHPrce);
    }

    @Bean(name = "bindingphprce")
    Binding binding(@Qualifier(QUEUE_PHPrce)Queue queue,@Qualifier(EXCHANGE_PHPrce) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

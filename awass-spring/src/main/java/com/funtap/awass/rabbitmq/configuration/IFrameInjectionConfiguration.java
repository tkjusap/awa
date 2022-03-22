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
public class IFrameInjectionConfiguration {
    public static final String EXCHANGE_IFi = "ifi-exchange";
    public static final String QUEUE_IFi = "ifi-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_IFi)
    Queue queue() {
        return new Queue(QUEUE_IFi, false);
    }

    @Bean(name = EXCHANGE_IFi)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_IFi);
    }

    @Bean(name = "bindingifi")
    Binding binding(@Qualifier(QUEUE_IFi)Queue queue, @Qualifier(EXCHANGE_IFi) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

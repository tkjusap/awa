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
public class XMLinectionConfiguration {
    public static final String EXCHANGE_XMLi = "xmli-exchange";
    public static final String QUEUE_XMLi = "xmli-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_XMLi)
    Queue queue() {
        return new Queue(QUEUE_XMLi, false);
    }

    @Bean(name = EXCHANGE_XMLi)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_XMLi);
    }

    @Bean(name = "bindingxmli")
    Binding binding(@Qualifier(QUEUE_XMLi)Queue queue, @Qualifier(EXCHANGE_XMLi) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

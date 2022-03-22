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
public class TextFilesAccountsConfiguration {
    public static final String EXCHANGE_Text = "text-exchange";
    public static final String QUEUE_Text = "text-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_Text)
    Queue queue() {
        return new Queue(QUEUE_Text, false);
    }

    @Bean(name = EXCHANGE_Text)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_Text);
    }

    @Bean(name = "bindingtext")
    Binding binding(@Qualifier(QUEUE_Text)Queue queue,@Qualifier(EXCHANGE_Text) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

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
public class BackupAndUnreferencedFileConfiguration {
    public static final String EXCHANGE_Backup = "backup-exchange";
    public static final String QUEUE_Backup = "backup-queue";
    public static final String ROUTING_KEY = "rabbitmq.*";
    private CountDownLatch latch = new CountDownLatch(1);

    @Bean(name = QUEUE_Backup)
    Queue queue() {
        return new Queue(QUEUE_Backup, false);
    }

    @Bean(name = EXCHANGE_Backup)
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_Backup);
    }

    @Bean(name = "bindingbackup")
    Binding binding(@Qualifier(QUEUE_Backup)Queue queue,@Qualifier(EXCHANGE_Backup) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

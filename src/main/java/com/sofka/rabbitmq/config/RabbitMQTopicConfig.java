package com.sofka.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopicConfig {

    @Bean
    Queue apartamento101() {
        return new Queue("101", false);
    }

    @Bean
    Queue apartamento102() {
        return new Queue("102", false);
    }

    @Bean
    Queue apartamento201() {
        return new Queue("201", false);
    }

    @Bean
    Queue apartamento202() {
        return new Queue("202", false);
    }

    @Bean
    Queue apartamento301() {
        return new Queue("301", false);
    }

    @Bean
    Queue apartamento302() {
        return new Queue("302", false);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topic-exchange");
    }

    @Bean
    Binding apartamento101Binding(Queue apartamento101, TopicExchange topicExchange) {
        return BindingBuilder.bind(apartamento101).to(topicExchange).with("queue.impar");
    }

    @Bean
    Binding apartamento102Binding(Queue apartamento102, TopicExchange topicExchange) {
        return BindingBuilder.bind(apartamento102).to(topicExchange).with("queue.par");
    }

    @Bean
    Binding apartamento201Binding(Queue apartamento201, TopicExchange topicExchange) {
        return BindingBuilder.bind(apartamento201).to(topicExchange).with("queue.impar");
    }

    @Bean
    Binding apartamento202Binding(Queue apartamento201, TopicExchange topicExchange) {
        return BindingBuilder.bind(apartamento201).to(topicExchange).with("queue.par");
    }

    @Bean
    Binding apartamento301Binding(Queue apartamento301, TopicExchange topicExchange) {
        return BindingBuilder.bind(apartamento301).to(topicExchange).with("queue.impar");
    }

    @Bean
    Binding apartamento302Binding(Queue apartamento302, TopicExchange topicExchange) {
        return BindingBuilder.bind(apartamento302).to(topicExchange).with("queue.par");
    }




    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        return simpleMessageListenerContainer;
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}

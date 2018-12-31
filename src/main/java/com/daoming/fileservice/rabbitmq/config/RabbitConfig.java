package com.daoming.fileservice.rabbitmq.config;

import com.daoming.fileservice.rabbitmq.Receiver.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : daoming.wang
 * @date : 2018/12/30
 */
@Configuration
//@ConfigurationProperties(prefix = "rabbitmq")
//@PropertySource("classpath:rabbitMQ.properties")
public class RabbitConfig {
    private String host;
    private Integer port;
    private String username;
    private String password;

    private String publisherconfirms;
    private String virtualhost;


    //声明队列
    @Bean
    public Queue queue1() {
        return new Queue("queue1", true); // true表示持久化该队列
    }

    @Bean
    public Queue queue2() {
        return new Queue("queue2", true);
    }

    //声明交互器
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    //绑定
    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queue1()).to(topicExchange()).with("key.1");
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queue2()).to(topicExchange()).with("key.2");
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }


}

package com.daoming.fileservice.rabbitmq.Receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @author : daoming.wang
 * @date : 2018/12/30
 */
@Component
public class Receiver {

    @RabbitListener(queues = "queue1")
    public String processMessage1(String msg) {
        System.out.println(Thread.currentThread().getName() + " 接收到来自queue1队列的消息：" + msg);
        return msg.toUpperCase();
    }

    @RabbitListener(queues = "queue2")
    public void processMessage2(String msg) {
        System.out.println(Thread.currentThread().getName() + " 接收到来自queue2队列的消息：" + msg);
    }
}

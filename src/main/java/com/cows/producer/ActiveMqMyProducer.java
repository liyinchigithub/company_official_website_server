package com.cows.producer;


import jakarta.annotation.Resource;
import jakarta.jms.Destination;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * ActiveMQ 消息发送者
 * @author liyinchi
 */
@Service
public class ActiveMqMyProducer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(Destination destination, String msg) {
        jmsMessagingTemplate.convertAndSend(destination, msg);
    }
}
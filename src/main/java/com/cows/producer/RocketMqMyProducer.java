//package com.example.lyc.springboot.demo.producer;
//
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * RocketMQ  生产者
// * 发送消息
// * */
//@Component
//public class RocketMqMyProducer {
//    @Autowired
//    private RocketMQTemplate rocketMQTemplate;
//
//    public void send(String topic, String message) {
//        rocketMQTemplate.convertAndSend(topic, message);
//    }
//}

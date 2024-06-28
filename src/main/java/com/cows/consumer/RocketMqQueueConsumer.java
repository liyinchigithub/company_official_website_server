//package com.example.lyc.springboot.demo.consumer;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Service;
//
//
///**
// * RocketMQ 消息队列 消费者
// * @author liyinchi
// * */
//@Slf4j
//@Service
////
//@RocketMQMessageListener(topic = "my-topic", consumerGroup = "my-consumer-group") // topic监听的主题，即当生产者发送消息到这个主题时，RocketMQ会将这些消息路由到监听这个主题的消费者
//public class RocketMqQueueConsumer implements RocketMQListener<String> {
//    @Override
//    public void onMessage(String message) {
//        System.out.println("Received message: " + message);
//        log.info("RocketMQ 消费者 接收消息: " + message);
//    }
//}
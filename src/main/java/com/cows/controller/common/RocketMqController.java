//package com.example.lyc.springboot.demo.controller;
//
//import com.example.lyc.springboot.demo.producer.RocketMqMyProducer;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * RocketMQ 控制器
// * @author liyinchi
// * */
//@Slf4j
//@RestController
//@RequestMapping("/rocketmq")
//public class RocketMqController {
//    @Autowired
//    private RocketMqMyProducer rocketMqMyProducer;
//
//    @GetMapping("/send/queue")
//    public String send() {
//        rocketMqMyProducer.send("my-topic", "Hello, RocketMQ!");
//        log.info("RocketMQ 生产者 Message sent");
//        return "Message sent";
//    }
//}
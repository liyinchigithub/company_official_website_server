package com.cows.controller;

import com.cows.producer.ActiveMqMyProducer;
import jakarta.annotation.Resource;
import jakarta.jms.Destination;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ActiveMQ 控制器
 * @author liyinchi
 */
@Slf4j
@RestController
@RequestMapping("/activemq")
public class ActiveMqController {
    private static final Logger logger = LoggerFactory.getLogger(ActiveMqController.class);
    @Resource
    private ActiveMqMyProducer producer;
    @Resource
    private Destination queue;

    @GetMapping("/send/queue")
    public String sendQueueMessage() {
        logger.info("===开始发送点对点消息===");
        producer.sendMessage(queue, "Queue: hello activemq!");
        log.info("===生产者 发送点对点消息===");
        return "success";
    }
}
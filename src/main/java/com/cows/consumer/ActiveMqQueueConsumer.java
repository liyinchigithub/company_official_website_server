package com.cows.consumer;

import com.cows.config.ActiveMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * ActiveMQ 消息消费者
 * @author liyinchi
 */
@Slf4j
@Service
public class ActiveMqQueueConsumer {

    /**
     * 接收点对点消息
     * @param msg
     */
    @JmsListener(destination = ActiveMqConfig.QUEUE_NAME)//
    public void receiveQueueMsg(String msg) {
        log.info("ActiveMqQueueConsumer 消费者 收到的消息为：" + msg);
    }
}

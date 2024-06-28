package com.cows.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
/**
 * WebSocket处理类
 * */
public class MyWebSocketHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyWebSocketHandler.class);

    /**
     * afterConnectionEstablished方法会在WebSocket连接建立后调用
     * @param session WebSocket会话
     * */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("New WebSocket connection, id: {}", session.getId());
    }

    /**
     * handleTextMessage方法会在接收到新的WebSocket消息时被调用
     *  @param session WebSocket会话
     *  @param message 接收到的消息
     * */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //  处理接收到的消息
        logger.info("Received message: {}", message.getPayload());
        // 等待10秒后再返回消息
        Thread.sleep(1000);
        //  构造返回消息
        TextMessage returnMessage = new TextMessage("Message '" + message.getPayload() + "' received at server");
        // 发送给客户端
        session.sendMessage(returnMessage);
    }

    /**
     * afterConnectionEstablished方法会在新的WebSocket连接建立后被调用
     * @param session WebSocket会话
     * @param status 关闭连接的状态
     * */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("WebSocket connection closed, id: {}", session.getId());
    }
}

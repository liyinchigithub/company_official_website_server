//package com.example.lyc.springboot.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.Socket;
//import java.util.Arrays;
//import java.util.Base64;
//
//@Controller
//public class ScreenController {
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//
//    @GetMapping("/start")
//    public void startScreenSharing() throws IOException {
//        // 启动minicap
//        ProcessBuilder minicapProcessBuilder = new ProcessBuilder("adb", "shell", "LD_LIBRARY_PATH=/data/local/tmp", "/data/local/tmp/minicap", "-P", "1080x1920@1080x1920/0");
//        Process minicapProcess = minicapProcessBuilder.start();
//
//        // 连接到minicap
//        Socket minicapSocket = new Socket("localhost", 1313);
//        InputStream minicapInputStream = minicapSocket.getInputStream();
//
//        // 从minicap读取屏幕截图并发送到客户端
//        byte[] buffer = new byte[4096];
//        int bytesRead;
//        while ((bytesRead = minicapInputStream.read(buffer)) != -1) {
//            String screenshot = Base64.getEncoder().encodeToString(Arrays.copyOf(buffer, bytesRead));
//            messagingTemplate.convertAndSend("/topic/screen", screenshot);
//        }
//    }
//}

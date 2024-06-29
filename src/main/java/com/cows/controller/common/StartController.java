package com.cows.controller.common;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一个简单的控制器，用于测试Spring Boot是否正常运行。
 * @author liyinchi
 * @date 2024/06/29
 */
@RestController
@RequestMapping("/start")
public class StartController {

    @RequestMapping("/springbok")
    public String startSpringBoot() {
        return "Welcome to the world of Spring Boot!";
    }
}

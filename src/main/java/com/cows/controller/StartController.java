package com.cows.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/start")
public class StartController {

    @RequestMapping("/springbok")
    public String startSpringBoot() {
        return "Welcome to the world of Spring Boot!";
    }
}

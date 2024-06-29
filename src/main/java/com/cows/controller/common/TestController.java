package com.cows.controller.common;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cows.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 一个简单的测试接口
 * @author liyinchi
 * @date 2023/12/14
 * */
@Slf4j
@RestController
@RequestMapping("/v1/test")
public class TestController {
        @RequestMapping("/interceptor")
        public String test() {
            return "hello";
    }

        @Resource
        private RedisService redisService;

        @GetMapping("/testRedis")
        public boolean testRedis() {
            log.info("testRedis");
            //
            return redisService.testRedisConnection();
        }
}



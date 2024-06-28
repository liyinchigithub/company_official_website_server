package com.cows;

import com.alibaba.fastjson.JSON;
import com.cows.entity.User;
import com.cows.service.RedisService;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests2 {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationTests2.class);

    @Resource
    private RedisService redisService;

    @Test
    public void contextLoads() {
        //测试redis的string类型
        redisService.setString("weichat","程序员私房菜");
        logger.info("我的微信公众号为：{}", redisService.getString("weichat"));

        // 如果是个实体，我们可以使用json工具转成json字符串，
        User user = new User(11,"CSDN", "123456",0);
        redisService.setString("userInfo", JSON.toJSONString(user));
        logger.info("用户信息：{}", redisService.getString("userInfo"));
    }
}
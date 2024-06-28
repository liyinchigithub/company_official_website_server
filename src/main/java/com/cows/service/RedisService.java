package com.cows.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Environment env;



    @PostConstruct
    public void printRedisPort() {
        System.out.println(env.getProperty("spring.redis.port"));
        log.info("redis port: {}", env.getProperty("spring.redis.port"));
    }
    /**
     * set redis: string类型
     *
     * @param key   key
     * @param value value
     */
    public void setString(String key, String value) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    /**
     * get redis: string类型
     *
     * @param key key
     * @return
     */
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }


    /**
     *
     * */
    public boolean testRedisConnection() {
        try {
            String testKey = "testKey" + System.currentTimeMillis(); // 使用独特的键
            String testValue = "testValue";
            setString(testKey, testValue);
            String returnedValue = getString(testKey);
            log.info("returnedValue: {}", returnedValue);
            stringRedisTemplate.delete(testKey); // 测试完成后删除键
            return testValue.equals(returnedValue);
        } catch (Exception e) {
            log.error("redis连接失败", e);
            return false;
        }
    }
}

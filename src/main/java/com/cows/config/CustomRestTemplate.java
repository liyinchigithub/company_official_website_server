package com.cows.config;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CustomRestTemplate {

    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // 获取默认的转换器列表
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>(restTemplate.getMessageConverters());

        // 添加自定义的StringHttpMessageConverter，处理text/plain类型的响应
        HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        messageConverters.add(converter); // 可以根据需要调整添加的位置

        // 设置自定义的转换器列表
        restTemplate.setMessageConverters(messageConverters);

        return restTemplate;
    }
}
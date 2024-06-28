package com.cows.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();

    // 获取默认的转换器列表
    List<HttpMessageConverter<?>> messageConverters = new ArrayList<>(restTemplate.getMessageConverters());

    // 添加自定义的StringHttpMessageConverter，处理text/plain类型的响应
    HttpMessageConverter<?> stringConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
    messageConverters.add(stringConverter);

    // 添加一个转换器来处理text/plain为JSON
    MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
    jsonConverter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
    messageConverters.add(jsonConverter);

    // 设置自定义的转换器列表
    restTemplate.setMessageConverters(messageConverters);

    return restTemplate;
}
}
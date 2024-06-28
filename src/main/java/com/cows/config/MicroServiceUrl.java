package com.cows.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ConfigurationProperties(prefix = "microservice.url")
@Data
public class MicroServiceUrl {
    private String orderUrl;
    private String userUrl;
    private String shoppingUrl;
}

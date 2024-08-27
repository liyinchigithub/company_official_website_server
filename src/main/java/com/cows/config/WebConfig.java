package com.cows.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.context.annotation.Profile; 
/**
 *
 * 配置前端允许跨域的域名地址
 * 默认所有域名都可以跨域访问，allowedOrigins指定允许跨域域名地址
 *
 * */
@Configuration
@Profile({"dev", "prod"})
public class WebConfig implements WebMvcConfigurer {

    @Value("${cors.allowed-origins}") // Add this line
    private String[] allowedOrigins; // Add this line

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(allowedOrigins) // Update this line
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
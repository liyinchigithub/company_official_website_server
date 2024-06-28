package com.cows.config;

import com.cows.service.UserService;
import com.cows.serviceImpl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cows.mapper.UserMapper;
import org.springframework.web.client.RestTemplate;

/**
 * AppConfig 类定义了一个 UserService 类型的 Bean。
 *
 * 如果你有多个实现了UserService接口的类，你可以通过Spring的配置来控制哪一个实现类被注入。
 *
 * 这通常通过使用@Primary注解或者在配置文件中指定具体的Bean来实现。
 *
 * 例如，假设你有两个实现了UserService接口的类，UserServiceImpl和AnotherUserServiceImpl
 * 可以在你希望被优先注入的实现类上添加 @Primary 注解：
 *
 * @Service
 * @Primary
 * public class UserServiceImpl implements UserService {
 *     // ...
 * }
 *
 * @Service
 * public class AnotherUserServiceImpl implements UserService {
 *     // ...
 * }
 *
 * 当 Spring 需要注入一个 UserService 类型的 Bean 时，它会使用 AppConfig 类中定义的 userService 方法返回的 Bean。
 *
 * 该类是 Spring 配置类的标准写法，在 Spring Boot 中，该类被 @SpringBootApplication 标注，所以
 * 该类也称为启动类。
 *
 * */
@Configuration
public class AppConfig {
    /**
     * 用于指定UserService接口的实现类
     * */
    @Bean
    public UserService userService(UserMapper userMapper) {
        return new UserServiceImpl(userMapper);
    }

    /**
     * 用于Dao转Dto
     * */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    /**
     * 用于RestTemplate
     * */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
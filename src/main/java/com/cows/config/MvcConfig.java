package com.cows.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 *
 * @Description: 配置静态资源路径
 * 在这个例子中，addResourceHandlers方法会添加一个资源处理器，
 * 这个资源处理器会将URL路径/files/**映射到文件系统的/absolute/path/to/your/directory/。这样，你就可以通过访问http://your-server.com/files/your-file.jpg来访问/absolute/path/to/your/directory/your-file.jpg。
 * 请注意，你需要将/absolute/path/to/your/directory/替换为你的文件夹的绝对路径。并且，路径应该以/结束。
 *
 * */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:./upload/");
        registry.addResourceHandler("/mp3/**")
                .addResourceLocations("classpath:/media/");
    }
}

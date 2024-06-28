package com.cows.commons.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author tiltwind
 * @Description 基础响应
 * 用于构建响应格式的类
 */
@Data  // Lombok注解，自动生成getter、setter、equals、hashCode和toString方法
@Component  // Spring注解，将此类标记为Spring容器管理的Bean
public class BaseResponse<T> {
    public static final int CODE_SUCCESS = 0;  // 成功的状态码
    private int code;  // 响应的状态码
    private String message;  // 响应的消息

    @JsonInclude(JsonInclude.Include.NON_NULL)  // Jackson注解，序列化JSON时忽略null值
    private T data;  // 响应的数据

    // 返回成功的响应，没有数据：success()
    public BaseResponse() {  // 默认构造函数，初始化状态码和消息为成功
        this.code = 0;
        this.message = "success";
    }
    // 返回成功的响应，带有数据：success(data)
    public BaseResponse(int code, String message) {  // 构造函数，初始化状态码和消息
        this.code = code;
        this.message = message;
    }
    // 返回成功的响应，带有数据：success(data)
    public BaseResponse(T data) {  // 构造函数，初始化状态码、消息和数据为成功
        this.code = CODE_SUCCESS;
        this.message = "success";
        this.data = data;
    }
    //  返回成功的响应，没有数据：success()
    public static BaseResponse<String> success() {  // 静态方法，返回一个成功的响应，没有数据
        return new BaseResponse<>("");
    }
    //  返回成功的响应，带有数据：success(data)
    public static <T> BaseResponse<T> success(T data) {  // 静态方法，返回一个成功的响应，带有数据
        return new BaseResponse<>(data);
    }
    // 返回自定义响应，带有数据：success(code, message, data)
    public BaseResponse(int code, String message, T data) {  // 构造函数，初始化状态码、消息和数据
        this.code = code;
        this.message = message;
        this.data = data;
    }
    //  返回失败的响应，状态码为1，没有数据：error("msg")
    public static BaseResponse<?> error(String msg) {  // 静态方法，返回一个错误的响应，状态码为1，没有数据
        return new BaseResponse<>(1, msg);
    }
    //  返回失败的响应，状态码和消息可自定义，没有数据：error(code, "msg")
    public static BaseResponse<?> error(int code, String msg) {  // 静态方法，返回一个错误的响应，状态码和消息可自定义，没有数据
        return new BaseResponse<>(code, msg);
    }
}
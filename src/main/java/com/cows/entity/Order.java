package com.cows.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

/***
 * Order 实体类
 * 层级：模型层(Model)
 * 作用：
 * 属性：
 */

@Data   //  自动生成getter/setter方法
@AllArgsConstructor //   自动生成全参构造器
@NoArgsConstructor //  自动生成无参构造器
@Component //  注册到spring容器
@Schema(name = "Order", description = "订单实体类") // swagger 标注
public class Order {
    private int id;
    private int userId;
    private BigDecimal totalPrice; // 总价，使用BigDecimal类型以避免精度问题
    private String status;
    private LocalDateTime createTime; // 使用Java 8的日期时间API
    private LocalDateTime updateTime;

    /* 省略get、set 和 带参构造方法、无参构造函数 */

}

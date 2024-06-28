package com.cows.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/***
 * UserManager 实体类
 * 层级：模型层(Model)
 */

@Data   //  自动生成getter/setter方法
@AllArgsConstructor //   自动生成全参构造器
@NoArgsConstructor //  自动生成无参构造器
@Component //  注册到spring容器
@Schema(name = "UserManager", description = "管理员用户实体类") // swagger 标注
public class UserManager {
    @Schema(name = "id", description = "用户id")
    private int id;
    @Schema(name = "userName", description = "用户名")
    private String userName;
    @Schema(name = "password", description = "密码")
    private String password;
    @Schema(name = "role", description = "角色")
    private String role;
    private int updates;

    /* 省略get、set 和 带参构造方法、无参构造函数 */

}

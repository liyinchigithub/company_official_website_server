package com.cows.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Schema(name = "Admin", description = "管理员实体类")
public class Admin {
    @Schema(name = "id", description = "管理员ID")
    private int id;
    @Schema(name = "userName", description = "用户名")
    private String userName;
    @Schema(name = "password", description = "密码")
    private String password;
    @Schema(name = "permissions", description = "权限")
    private String permissions;
    @Schema(name = "updateTime", description = "更新时间") // 修改字段名
    private String updateTime;
    @Schema(name = "createTime", description = "创建时间") // 修改字段名
    private String createTime;
    @Schema(name = "remarks", description = "备注")
    private String remarks;
    @Schema(name = "isDeleted", description = "是否删除")
    private boolean isDeleted;
    @Schema(name = "isEnable", description = "是否启用") // 新增字段
    private boolean isEnable;
}
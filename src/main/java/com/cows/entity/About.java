package com.cows.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
@Component
@Schema(name = "About", description = "关于我们页面图片实体类")
public class About {
    @Schema(name = "id", description = "图片ID")
    private int id;
    @Schema(name = "imageUrl", description = "图片URL")
    private String imageUrl;
    @Schema(name = "description", description = "图片描述")
    private String description;
    @Schema(name = "updateTime", description = "更新时间")
    private String updateTime;
    @Schema(name = "createTime", description = "创建时间")
    private String createTime;
    @Schema(name = "isDeleted", description = "是否删除")
    private boolean isDeleted;
}
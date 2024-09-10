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
@Schema(name = "Carousel", description = "轮播图实体类")
public class Carousel {
    @Schema(name = "id", description = "轮播图ID")
    private int id;
    @Schema(name = "title", description = "轮播图标题")
    private String title;
    @Schema(name = "order", description = "轮播图排序")
    private int order;
    @Schema(name = "imageUrl", description = "图片URL地址")
    private String imageUrl;
    @Schema(name = "redirectUrl", description = "图片跳转地址")
    private String redirectUrl;
    @Schema(name = "isEnabled", description = "是否启用")
    private boolean isEnabled;
    @Schema(name = "createTime", description = "创建时间")
    private String createTime;
    @Schema(name = "updateTime", description = "修改时间")
    private String updateTime;
    @Schema(name = "isDeleted", description = "是否删除")
    private boolean isDeleted;
}
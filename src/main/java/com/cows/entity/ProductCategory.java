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
@Schema(name = "ProductCategory", description = "商品分类实体类")
public class ProductCategory {
    @Schema(name = "id", description = "商品分类ID")
    private int id;
    @Schema(name = "name", description = "商品分类名称")
    private String name;
    @Schema(name = "description", description = "商品分类描述")
    private String description;
    @Schema(name = "updateTime", description = "更新时间")
    private String updateTime;
    @Schema(name = "createTime", description = "创建时间")
    private String createTime;
    @Schema(name = "isEnabled", description = "启用禁用状态")
    private boolean isEnabled;
    @Schema(name = "isDeleted", description = "是否删除")
    private boolean isDeleted;
}
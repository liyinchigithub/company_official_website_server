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
@Schema(name = "ProductsCarousels", description = "横向轮播图商品组件配置实体类")
public class ProductsCarousels {
    @Schema(name = "id", description = "配置ID")
    private int id;
    @Schema(name = "products", description = "展示商品ID数组")
    private int[] products;
    @Schema(name = "createTime", description = "创建时间")
    private String createTime;
    @Schema(name = "updateTime", description = "更新时间")
    private String updateTime;
    @Schema(name = "isDeleted", description = "是否删除")
    private boolean isDeleted;
}
package com.cows.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@RequiredArgsConstructor
@Component
@Schema(name = "Product", description = "商品信息实体类")
public class Product {
    @Schema(name = "id", description = "商品ID")
    private int id;
    @Schema(name = "name", description = "商品名称")
    private String name;
    @Schema(name = "coverImage", description = "商品封面图片")
    private String coverImage;
    @Schema(name = "detailImages", description = "商品详情图片")
    private String[] detailImages;
    @Schema(name = "description", description = "商品描述")
    private String description;
    @Schema(name = "salePrice", description = "销售价格")
    private double salePrice;
    @Schema(name = "costPrice", description = "成本价格")
    private double costPrice;
    @Schema(name = "stockQuantity", description = "库存数量")
    private int stockQuantity;
    @Schema(name = "brand", description = "商品品牌")
    private String brand;
    @Schema(name = "categoryId", description = "商品分类ID")
    private int categoryId;
    @Schema(name = "updateTime", description = "更新时间")
    private String updateTime;
    @Schema(name = "createTime", description = "创建时间")
    private String createTime;
    @Schema(name = "isAvailable", description = "上下架状态")
    private boolean isAvailable;
    @Schema(name = "isDeleted", description = "是否删除")
    private boolean isDeleted;
}
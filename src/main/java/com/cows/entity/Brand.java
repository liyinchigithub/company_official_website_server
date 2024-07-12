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
@Schema(name = "Brand", description = "品牌信息实体类")
public class Brand {
    @Schema(name = "id", description = "品牌ID")
    private int id;
    @Schema(name = "name", description = "品牌名称")
    private String name;
    @Schema(name = "description", description = "品牌描述")
    private String description;
    @Schema(name = "logo", description = "品牌Logo")
    private String logo;
    @Schema(name = "createTime", description = "创建时间")
    private String createTime;
    @Schema(name = "updateTime", description = "更新时间")
    private String updateTime;
    @Schema(name = "isDeleted", description = "是否删除")
    private boolean isDeleted;
}
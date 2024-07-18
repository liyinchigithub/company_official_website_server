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
@Schema(name = "BrandAuthorizationCertificate", description = "品牌授权书实体类")
public class BrandAuthorizationCertificate {
    @Schema(name = "id", description = "证书ID")
    private int id;
    @Schema(name = "name", description = "证书名称")
    private String name;
    @Schema(name = "description", description = "证书描述")
    private String description;
    @Schema(name = "imageUrl", description = "证书图片URL地址")
    private String imageUrl;
    @Schema(name = "createTime", description = "创建时间")
    private String createTime;
    @Schema(name = "updateTime", description = "更新时间")
    private String updateTime;
    @Schema(name = "isDeleted", description = "是否删除")
    private boolean isDeleted;
}
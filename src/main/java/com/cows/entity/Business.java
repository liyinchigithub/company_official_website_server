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
@Schema(name = "Business", description = "招商加盟信息实体类")
public class Business {
    @Schema(name = "id", description = "招商加盟ID")
    private int id;
    @Schema(name = "name", description = "招商加盟名称")
    private String name;
    @Schema(name = "description", description = "招商加盟描述")
    private String description;
    @Schema(name = "contactInfo", description = "联系方式")
    private String contactInfo;
    @Schema(name = "createTime", description = "创建时间")
    private String createTime;
    @Schema(name = "updateTime", description = "更新时间")
    private String updateTime;
    @Schema(name = "isDeleted", description = "是否删除")
    private boolean isDeleted;
}
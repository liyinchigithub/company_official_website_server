package com.cows.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

/**
 * 基本信息实体类
 */
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
@Component
@Schema(name = "BasicInformation", description = "网站基本信息")
public class BasicInformation {

    @Id
    @Schema(name = "id", description = "ID")
    private Long id;
    @Schema(name = "homeTitle", description = "首页标题")
    private String homeTitle;
    @Schema(name = "homeDescription", description = "首页描述")
    private String homeDescription;
    @Schema(name = "phone", description = "联系方式")
    private String phone;
    @Schema(name = "email", description = "邮箱")
    private String email;
    @Schema(name = "address", description = "地址")
    private String address;
    @Schema(name = "weChatImage", description = "微信图片")
    private String weChatImage;
    @Schema(name = "icp", description = "ICP备案号")
    private String icp;
    @Schema(name = "beianImage", description = "备案图片")
    private String beianImage;
    @Schema(name = "publicSecurity", description = "公共安全")
    private String publicSecurity;
    @Schema(name = "copyright", description = "版权信息")
    private String copyright;
    @Schema(name = "isDeleted", description = "是否删除")
    private boolean isDeleted;
    @Schema(name = "isEnable", description = "是否启用")
    private boolean isEnable;
}
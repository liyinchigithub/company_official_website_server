package com.cows.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WechatSDKConfig {
    private String appId;
    private String timestamp;
    private String nonceStr;
    private String signature;
    // Getters and Setters
}

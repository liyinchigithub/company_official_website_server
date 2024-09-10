package com.cows.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
public class WechatUser {
    private String openId; // 用户的唯一标识
    private String nickname; // 用户昵称
    private String gender; // 用户的性别，值为1时是男性，值为2时是女性
    private String city; // 城市
    private String country; // 国家
    private String province; // 省份
    private String avatarUrl; // 用户头像

    // Getters and Setters
    @Override
    public String toString() {
        log.info("WechatUser{" +
               "openId='" + openId + '\'' +
               ", nickname='" + nickname + '\'' +
               ", gender='" + gender + '\'' +
               ", city='" + city + '\'' +
               ", country='" + country + '\'' +
               ", province='" + province + '\'' +
               ", avatarUrl='" + avatarUrl + '\'' +
               '}');
        return "WechatUser{" +
               "openId='" + openId + '\'' +
               ", nickname='" + nickname + '\'' +
               ", gender='" + gender + '\'' +
               ", city='" + city + '\'' +
               ", country='" + country + '\'' +
               ", province='" + province + '\'' +
               ", avatarUrl='" + avatarUrl + '\'' +
               '}';
    }
}
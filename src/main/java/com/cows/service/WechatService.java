package com.cows.service;

import com.cows.entity.WechatUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.springframework.http.converter.StringHttpMessageConverter;

/**
 * 微信服务类
 * 用于获取微信用户信息
 * */ 
@Slf4j
@Service
public class WechatService {
    private static final long EXPIRATION_OFFSET = 300; // 提前5分钟刷新token
    @Value("${wechat.appid}")
    private String appId;

    @Value("${wechat.secret}")
    private String secret;

    @Value("${wechat.redirect.uri}")
    private String redirectUri;

    @Autowired
    private RestTemplate restTemplate;  // 确保使用@Autowired注解

    private String accessToken;
    private long accessTokenExpiresAt;
    private String refreshToken;
    private long refreshTokenExpiresAt; // refreshToken的过期时间
    private String openid;

    public WechatService() {
        this.restTemplate = new RestTemplate();
        // 添加对text/plain类型的支持
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    }

    // 实现根据code获取access_token的逻辑
    // 实现根据access_token获取用户信息的逻辑
    public synchronized AccessTokenResponse ensureValidAccessToken(String code) {
        long now = System.currentTimeMillis() / 1000;
        if (accessToken != null && (accessTokenExpiresAt - now > EXPIRATION_OFFSET)) {
            log.info("使用现有的accessToken");
            return new AccessTokenResponse(accessToken, openid, refreshToken, accessTokenExpiresAt - now, ""); // 确保openid已经被设置
        }
    
        if (refreshToken != null && canRefreshToken(now)) {
            log.info("尝试使用refreshToken刷新accessToken");
            AccessTokenResponse response = refreshAccessToken(refreshToken);
            if (response != null && response.getAccessToken() != null) {
                updateToken(response);
                log.info("成功刷新accessToken");
                return response;
            }
        }
    
        log.info("请求新的accessToken");
        AccessTokenResponse response = requestNewAccessToken(code); // 传递授权码
        updateToken(response);
        log.info("成功获取新的accessToken");
        return response;
    }

    private boolean canRefreshToken(long currentTime) {
        // 实现检查 refreshToken 是否过期的逻辑
        log.info("检查refreshToken是否过期");
        System.out.println("检查refreshToken是否过期");
        log.info("当前时间: " + currentTime);
        log.info("refreshTokenExpiresAt: " + refreshTokenExpiresAt);
        log.info("EXPIRATION_OFFSET: " + EXPIRATION_OFFSET);
        boolean isTokenValid = currentTime < refreshTokenExpiresAt - EXPIRATION_OFFSET;
        // 此处假设 refreshToken 的有效期通常比 accessToken 长很多
        log.info("refreshToken是否有效: " + isTokenValid);
        return isTokenValid;
    
    }

    private void updateToken(AccessTokenResponse response) {
        long now = System.currentTimeMillis() / 1000;
        this.accessToken = response.getAccessToken();
        this.refreshToken = response.getRefreshToken();
        this.accessTokenExpiresAt = now + response.getExpiresIn();
        this.refreshTokenExpiresAt = now + 2592000; // 假设 refreshToken 有效期为30天
        this.openid = response.getOpenid(); // 确保更新openid
        log.info("更新了accessToken和refreshToken，新的accessToken过期时间为：" + this.accessTokenExpiresAt);
        log.info("新的refreshToken过期时间为：" + this.refreshTokenExpiresAt);
        log.info("更新了openid：" + this.openid);
    }

    // 通用方法获取access_token
    // 微信服务类中的方法
    public AccessTokenResponse getAccessToken(String code, String grantType) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("appid", appId)
                .queryParam("secret", secret)
                .queryParam("code", code)
                .queryParam("grant_type", grantType);
                try {
        String response = restTemplate.getForObject(builder.toUriString(), String.class);
        System.out.println("微信API响应: " + response);
        // 
        Gson gson = new Gson();
        AccessTokenResponse tokenResponse = gson.fromJson(response, AccessTokenResponse.class);
        // 调试输出response中的 access_token和openid
        System.out.println("AccessToken: " + tokenResponse.getAccessToken());
        System.out.println("OpenID: " + tokenResponse.getOpenid());
        System.out.println("Expires In: " + tokenResponse.getExpiresIn());
        System.out.println("Refresh Token: " + tokenResponse.getRefreshToken());
        return tokenResponse;
        } catch (Exception e) {
            System.out.println("处理微信API响应时发生错误: " + e.getMessage());
                return null;
            }
    }

    // 刷新access_token
    private AccessTokenResponse refreshAccessToken(String refreshToken) {
        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("appid", appId)
                .queryParam("grant_type", "refresh_token")
                .queryParam("refresh_token", refreshToken);

        String response = restTemplate.getForObject(builder.toUriString(), String.class);
        System.out.println("refreshAccessToken 微信API响应: " + response);
        return new Gson().fromJson(response, AccessTokenResponse.class);
    }

    // private AccessTokenResponse requestNewAccessToken() {
    //     String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
    //     UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
    //             .queryParam("appid", appId)
    //             .queryParam("secret", secret)
    //             .queryParam("code", "the_auth_code") // 这里的 code 应该是动态获取的
    //             .queryParam("grant_type", "authorization_code");

    //     String response = restTemplate.getForObject(builder.toUriString(), String.class);
    //     System.out.println("requestNewAccessToken 微信API响应: " + response);
    //     return new Gson().fromJson(response, AccessTokenResponse.class);
    // }
    private AccessTokenResponse requestNewAccessToken(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("appid", appId)
                .queryParam("secret", secret)
                .queryParam("code", code) // 确保这里使用的是动态获取的code
                .queryParam("grant_type", "authorization_code");
    
        String response = restTemplate.getForObject(builder.toUriString(), String.class);
        System.out.println("requestNewAccessToken 微信API响应: " + response);
        return new Gson().fromJson(response, AccessTokenResponse.class);
    }

    // 获取用户信息
    public WechatUser getUserInfo(String accessToken, String openid) {
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.weixin.qq.com/sns/userinfo")
        .queryParam("access_token", accessToken)
        .queryParam("openid", openid)
        .queryParam("lang", "zh_CN");

        String url = builder.toUriString();
        log.info("请求微信用户信息URL: " + url);
        WechatUser user = restTemplate.getForObject(url, WechatUser.class);
        System.out.println("微信用户信息响应: " + user);
        log.info("微信用户信息响应: " + user);
       return user;
}

    // 内部类用于解析access_token响应
    public static class AccessTokenResponse {
        @SerializedName("access_token")
        private String accessToken;
        @SerializedName("openid")
        private String openid;
        @SerializedName("expires_in")
        private long expiresIn;
        @SerializedName("refresh_token")
        private String refreshToken;
    
        private String scope;

         // 构造函数
        public AccessTokenResponse(String accessToken, String openid, String refreshToken, long expiresIn, String scope) {
            this.accessToken = accessToken;
            this.openid = openid;
            this.refreshToken = refreshToken;
            this.expiresIn = expiresIn;
            this.scope = scope;
        }

        // getter和setter方法
        // 获取accessToken
        public String getAccessToken() {
            return accessToken;
        }
        // 设置accessToken
        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
        // 获取refreshToken
        public String getRefreshToken() {
            return refreshToken;
        }
        // 设置refreshToken
        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }
        // 获取expiresIn
        public long getExpiresIn() {
            return expiresIn;
        }
        // 设置expiresIn
        public void setExpiresIn(long expiresIn) {
            this.expiresIn = expiresIn;
        }

        // 获取openid
        public String getOpenid() {
            return openid;
        }
        // 设置openid
        public void setOpenid(String openid) {
            this.openid = openid;
        }
    }

    
    
}
package com.cows.controller.common;

import com.cows.dto.LoginRequestDTO;
import com.cows.entity.User;
import com.cows.entity.WechatUser;
import com.cows.service.UserService;
import com.cows.util.JwtUtil;
import com.cows.service.WechatService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import java.util.Arrays;
import java.util.Collections;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 一个简单的登录控制器，用于处理登录请求。
 * @author liyinchi
 * @date 2024/06/29
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private final WechatService wechatService;
    @Autowired
    private UserService userService;
    @Autowired
    private Environment env;

    @Value("${wechat.appid}")
    private String appid;// 从application-dev.yml 获取微信小程序的appid

    @Value("${wechat.redirect.uri}")
    private String redirectUri;// 从application-dev.yml 获取微信小程序的回调地址

    public LoginController(WechatService wechatService) {
        this.wechatService = wechatService;
    }

    @Autowired
    public LoginController(WechatService wechatService, AuthenticationManager authenticationManager) {
        this.wechatService = wechatService;
        this.authenticationManager = authenticationManager;
    }

    // 登录页面
    @GetMapping
    public String login() {
        return "login"; // 返回login.html
    }

    
   // 账号密码登录
   @PostMapping("/perform_login")
    public ResponseEntity<?> performLogin(@RequestBody LoginRequestDTO loginRequest) {
        try {
            System.out.println("Received login request for user: " + loginRequest.getUsername());
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            String jwt = JwtUtil.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok().body(Collections.singletonMap("token", jwt));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    // H5微信登录
    @GetMapping("/wechat")
    public ModelAndView wechatLogin() {
//        // String appid = env.getProperty("wechat.appid");// 从application.properties获取appid
//        // String redirectUri = env.getProperty("wechat.redirect.uri");//
//        // 构建微信登录URL
//        String url = "https://open.weixin.qq.com/connect/qrconnect?appid=" +
//        appid +
//        "&redirect_uri=" +
//        redirectUri +
//        "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
//        // 这里应该是重定向到微信的授权页面，具体URL根据实际情况填写
//        return new ModelAndView("redirect:" + url);
        try {
            // String appid = env.getProperty("wechat.appid");// 从application.properties获取appid
            // String redirectUri = env.getProperty("wechat.redirect.uri");//
            // 构建微信登录URL
            // 对redirectUri进行URL编码
            String encodedRedirectUri = URLEncoder.encode(redirectUri, StandardCharsets.UTF_8.toString());
            String url = "https://open.weixin.qq.com/connect/qrconnect?appid=" +
                    appid +
                    "&redirect_uri=" +
                    encodedRedirectUri +
                    "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
            // 这里应该是重定向到微信的授权页面，具体URL根据实际情况填写
            return new ModelAndView("redirect:" + url);
        } catch (Exception e) {
            // 处理编码异常
            e.printStackTrace();
            return new ModelAndView("error"); // 或者返回一个错误页面
        }


    }

    // H5微信回调
    @GetMapping("/wechat/callback")
    // 方法返回类型可以是String、json、ResponseEntity
    public ResponseEntity<?> wechatCallback(@RequestParam String code, @RequestParam String state) {
        // 调用服务获取access_token
        // WechatService.AccessTokenResponse tokenResponse = wechatService.getAccessToken(code, "authorization_code");
        
        // 当方法返回类型是String时，可以直接返回
        // if (tokenResponse == null || tokenResponse.getAccessToken() == null) {
        //     return "error"; // 或者重定向到错误页面
        // }

        // 当方法返回类型是json时，可以直接返回
        // if (tokenResponse == null || tokenResponse.getAccessToken() == null) {
        //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to retrieve access token");
        // }

        // String accessToken = tokenResponse.getAccessToken();
        // String openid = tokenResponse.getOpenid();
        // System.out.println("AccessToken: " + accessToken);
        // System.out.println("OpenID: " + openid);
        
        // 使用access_token获取用户信息
        // WechatUser wechatUser = wechatService.getUserInfo(accessToken, openid);
        
        // 当方法返回类型是String时，可以直接返回
        // if (wechatUser == null) {
        //     System.out.println("未能获取微信用户信息");
        //     return "error";
        // }
        
        // 首先确保 accessToken 是有效的
        WechatService.AccessTokenResponse tokenResponse = wechatService.ensureValidAccessToken(code);

        // 使用有效的 accessToken 和 openid 获取用户信息
        WechatUser wechatUser = wechatService.getUserInfo(tokenResponse.getAccessToken(), tokenResponse.getOpenid());

        // 当方法返回类型是json时，可以直接返回
        if (wechatUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        
        // 根据业务逻辑处理用户信息，例如创建用户、生成JWT等
        wechatUser.setOpenId(tokenResponse.getOpenid()); // 确保设置了openid
        User user = userService.loginOrCreateWechatUser(wechatUser);// 检查或创建用户

        // 当方法返回类型是String时，可以直接返回
        // return "userProfile"; // 或者重定向到其他页面

       // 创建一个包含用户和微信用户信息的响应对象
        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("wechatUser", wechatUser);

        // 返回包含用户信息和微信用户信息的 JSON
        return ResponseEntity.ok(response);
    }

    // 验证微信回调
    @GetMapping("/wechat/verify")
    @ResponseBody  // 添加这个注解
    public String verifyWechatToken(
            @RequestParam(name = "signature") String signature,
            @RequestParam(name = "timestamp") String timestamp,
            @RequestParam(name = "nonce") String nonce,
            @RequestParam(name = "echostr") String echostr) {
            //  调试输出
            System.out.println("signature: " + signature );
            System.out.println("timestamp: " + timestamp );
            System.out.println("nonce: " + nonce );
            System.out.println("echostr: " + echostr );
            if (checkSignature(signature, timestamp, nonce)) {
                System.err.println("验证成功");
                // return "success";
                return echostr; // 验证成功，返回echostr
            } else {
                System.err.println("验证失败");
                return "failure";
            }
    }
    // 验证签名
    private boolean checkSignature(String signature, String timestamp, String nonce) {
        String token = "123456"; // 这里填写你在微信公众平台上设置的Token
        String[] arr = new String[] { token, timestamp, nonce };
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (String str : arr) {
            content.append(str);
        }
        // 将三个参数字符串拼接成一个字符串进行sha1加密
        String calcSignature = DigestUtils.sha1Hex(content.toString());
        return calcSignature.equals(signature);
    }

    // APP登录
    @GetMapping("/app")
    public ResponseEntity<?> appLogin(@RequestParam String code) {
        // 使用 "authorization_code" 替换 "client_credential"
        String accessToken = wechatService.getAccessToken(code, "authorization_code").getAccessToken();
        // 由于 "client_credential" 不返回 openid，这里假设你已经有了正确的 code 来获取 openid
        String openid = wechatService.getAccessToken(code, "authorization_code").getOpenid();
        WechatUser user = wechatService.getUserInfo(accessToken, openid);
        // 处理用户信息，生成JWT等
        return ResponseEntity.ok().body(user);
    }
   
}
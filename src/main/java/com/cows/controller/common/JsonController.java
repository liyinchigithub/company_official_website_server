package com.cows.controller.common;

import com.cows.commons.api.BaseResponse;
import com.cows.entity.User;
import com.cows.commons.api.JsonResult;
import com.cows.exception.BusinessErrorException;
import com.cows.exception.BusinessMsgEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一个简单的json返回
 * @author liyinchi
 * @date 2024/06/29
 */
@Slf4j
@RestController
@RequestMapping("/v1")
@Schema(name="JsonController", description="JsonController")
@Tag(name = "JsonController tags")
public class JsonController {
    @Value("${microservice.url.orderUrl}")
    String orderUrl;// orderUrl变量值 就是application.yml的url.orderUrl值
    @Autowired
    private User user;// 注入
    @Autowired
    com.cows.config.MicroServiceUrl MicroServiceUrl; // 注入配置类
    @Autowired
    JsonResult jsonResult; // 注入配置类

    @GetMapping("/user")
    @ResponseBody
    @Operation(summary = "获取用户信息", description = "获取用户信息")
    public User getUser() {
        // TODO rom 查询数据库表
        user.setId(1);
        user.setUserName("李银池");
        user.setPassword("123456");
        // 日志输出
        log.info("user:{}", user);
        // 返回
        return user;
    }

    /***
     * @Description: list
     * @Author: liyinchi
     * @Date: 2023/9/18 11:17
     * @return map
     */
    @GetMapping("/list")
    @ResponseBody
    @Operation(summary = "获取用户列表", description = "获取用户列表")
    public List<User> getUserList() {
        // TODO rom 查询数据库表
        List<User> userList = new ArrayList<>();
        User user1 = new User(1, "李银池", "123456",0);
        User user2 = new User(2, "王哈哈", "123456",0);
        userList.add(user1);
        userList.add(user2);
        // 日志输出
        log.info("userList:{}", userList);
        // 返回数据
        return userList;
    }

    /***
     * @Description: map
     * @Author: liyinchi
     * @Date: 2023/9/18 11:17
     * @return map
     */
    @GetMapping("/map")
    @ResponseBody
    @Operation(summary = "获取用户信息", description = "获取用户信息")
    public Map<String, Object> getMap() {
        // TODO rom 查询数据库表
        Map<String, Object> map = new HashMap<>(3);
        User user = new User(1, "李银池", "123456",0);
        map.put("作者信息", user);
        map.put("github", "https://github.com/liyinchigithub");
        map.put("CSDN地址", "https://blog.csdn.net/u013302168");
        map.put("粉丝数量", 252);
        // 日志输出
        log.info("map:{}", map);
        // 返回
        return map;
    }

    /***
     * @Description: 读取application.yml配置文件参数
     * @Author: liyinchi
     * @Date: 2023/9/18 11:17
     * @return map
     */
    @GetMapping("/getYMLConfig")
    @ResponseBody
    @Operation(summary = "获取配置文件参数", description = "获取配置文件参数")
    public Map<String,Object> getYMLConfig() {
        Map<String,Object> orderUrlList = new HashMap();
        orderUrlList.put("microservice url orderUrl",orderUrl);
        log.info("orderUrl:{}", orderUrl);
        // 返回
        return (Map<String, Object>) new JsonResult(orderUrlList);
    }

    /***
     * @Description: 读取application.yml配置文件参数
     * @Author: liyinchi
     * @Date: 2023/9/18 11:17
     * @return map
     */
    @GetMapping("/getYMLConfigClass")
    @ResponseBody
    @Operation(summary = "获取配置文件参数", description = "获取配置文件参数")
    public Map<String,Object> getYMLConfigClass() {
        Map<String,Object> orderUrlList = new HashMap();
        orderUrlList.put("orderUrl",MicroServiceUrl.getOrderUrl());
        orderUrlList.put("userUrl",MicroServiceUrl.getUserUrl());
        orderUrlList.put("shoppingUrl",MicroServiceUrl.getShoppingUrl());
        log.info("getYMLConfigClass orderUrlMap:{}", orderUrlList);
        return orderUrlList;
    }

    /***
     * @Description: 获取请求体
     * @Date: 2023/9/18 14:00
     * @return map
     */
    @PostMapping("/addUser")
    @ResponseBody
    @Operation(summary = "获取请求体", description = "获取请求体")
    public Map<String,Object> getUser(@RequestBody @Parameter User user) { // 获取请求体
        // TODO rom 插入数据库表
        Map<String,Object> map = new HashMap();
        map.put("user",user);
        return map;
    }

    /**
     * 全局异常拦截测试
     * */
    @Slf4j
    @RestController
    @RequestMapping("/exception")
    @Schema(name="exception test", description="exception")
    @Tag(name = "异常测试")
    public static class ExceptionController {
        private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

        @PostMapping("/paramRequire")
        public BaseResponse paramRequire(@RequestParam("userName") String name,
                                         @RequestParam("password") String pass) {
            log.info("name：{}", name);
            log.info("pass：{}", pass);
            return new BaseResponse();
        }

        @PostMapping("/nullPointException")
        public BaseResponse nullPointException(@RequestBody String userName,
                               @RequestBody String password) {
            // 日志
            log.info("name：{}", userName);
            log.info("pass：{}", password);

            throw new NullPointerException("空指针异常");
    //        return new JsonResult();
        }

        /**
         *  业务异常，枚举类测试
         */
        @GetMapping("/business")
        public BaseResponse testException() {
            try {
                int i = 1 / 0;
            } catch (Exception e) {
                throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
            }
            return new BaseResponse();
        }

    }

    @Slf4j
    @RestController
    @RequestMapping("/v1")
    @Schema(name="获取图片文件", description="获取图片文件")
    @Tag(name = "获取图片文件")
    public static class ImageController {
        @GetMapping("/getLatestImage")
        public ResponseEntity<byte[]> getLatestImage(@RequestParam String fileName) throws IOException {
            Path path = Paths.get("./upload/" + fileName);
            if (!Files.exists(path)) {
                return ResponseEntity.notFound().build();
            }
            byte[] fileContent = Files.readAllBytes(path);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        }
    }

    // 这是一个返回内容是一个html页面的示例。
    @Slf4j
    @Controller
    @RequestMapping("/v1")
    @Schema(name="返回内容是一个html页面", description="返回内容是一个html页面")
    @Tag(name = "返回内容是一个html页面")
    @SessionAttributes("isGo") // 使用Spring的@SessionAttribute或者@SessionAttributes来在多个请求之间共享数据。
    public static class IoTController {

        @GetMapping("/page")
        public String getPage() {
            return "TestPage";
        }

        @GetMapping("/action")
        @ResponseBody
        public JsonResult<String> doAction(@RequestParam boolean isGo, Model model) {
            // 在这里处理isGo参数
            // 指定你想要存储在session中的属性名称
            model.addAttribute("isGo", isGo);
            return new JsonResult<>("actionResult");
        }

        @GetMapping("/getIsGo")
        @ResponseBody
        public JsonResult<Boolean> getIsGo(Model model) {
            Boolean isGo = (Boolean) model.getAttribute("isGo");
            return new JsonResult<>(isGo);
        }
    }
}
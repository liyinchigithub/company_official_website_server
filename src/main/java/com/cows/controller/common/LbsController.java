package com.cows.controller.common;

import com.cows.entity.LbsInfo;
import com.cows.service.LbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一个简单的LBS系统，使用Spring Boot和MySQL实现。
 * 用户可以上传自己的位置信息。
 * @author liyinchi
 * @date 2024/06/29
 */
@RestController
public class LbsController {
    @Autowired
    private LbsService lbsService;

    @PostMapping("/location")
    public void saveUserLocation(@RequestBody LbsInfo lbsInfo) {
        lbsService.saveLocationInfo(lbsInfo);
    }
}
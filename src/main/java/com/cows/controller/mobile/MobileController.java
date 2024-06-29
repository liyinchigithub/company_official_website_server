package com.cows.controller.mobile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  移动端接口
 * @author liyinchi
 * @date 2024/06/29
 */
@RestController
@RequestMapping("/mobile")
public class MobileController {

    @GetMapping("/info")
    public String getMobileInfo() {
        return "Mobile Info";
    }

    // 其他移动端的接口
}
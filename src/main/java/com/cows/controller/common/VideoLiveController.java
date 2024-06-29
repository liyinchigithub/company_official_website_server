package com.cows.controller.common;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 一个简单的控制器，用于处理视频直播的重定向请求。
 * 层级：控制器->服务层->数据访问层
 * @author liyinchi
 * @date 2024/06/29
 */
@Slf4j
@RestController
@RequestMapping("/v1")
@Schema(name="重定向", description="重定向")
@Tag(name = "重定向")
public class VideoLiveController {

    @GetMapping("/videoLive")
    public void redirectToVideoLive(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("http://10.224.7.237/mjpeg/1");
    }
}
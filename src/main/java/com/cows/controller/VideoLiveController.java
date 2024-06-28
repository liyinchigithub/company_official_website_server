package com.cows.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
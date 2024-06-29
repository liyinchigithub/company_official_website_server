package com.cows.controller.common;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import java.io.IOException;

/**
 * 一个简单的控制器，用于提供音频文件。
 * @author liyinchi
 * @date 2024/06/29
 */
@Slf4j
@RestController
@RequestMapping("/v1")
@Schema(name="获取音频文件", description="获取音频文件")
@Tag(name = "获取音频文件")
public class MediaController {

    @GetMapping("/media/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = new ClassPathResource("media/" + filename);
        try {
            file.getInputStream(); // check if file exists
            // 设置responseHeaders "audio/mpeg"为在线可播放
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("audio/mpeg"));
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"");

            return ResponseEntity.ok().headers(headers).body(file);
        } catch (IOException e) {
            log.error("File not found: {}", filename, e);
            return ResponseEntity.notFound().build();
        }
    }



}
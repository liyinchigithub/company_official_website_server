package com.cows.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RestController
@RequestMapping("/v1")
@Schema(name="获取图片文件", description="获取图片文件")
@Tag(name = "获取图片文件")
public class ImageController {
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
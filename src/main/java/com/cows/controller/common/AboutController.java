package com.cows.controller.common;

import com.cows.commons.api.BaseResponse;
import com.cows.entity.About;
import com.cows.service.AboutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/abouts")
public class AboutController {

    @Autowired
    private AboutService aboutService;

    @Operation(summary = "获取所有关于我们页面图片", description = "返回所有关于我们页面图片的列表")
    @GetMapping("/getAllAbouts")
    public BaseResponse<List<About>> getAllAbouts() {
        List<About> abouts = aboutService.getAllAbouts();
        log.info("获取所有关于我们页面图片成功");
        return BaseResponse.success(abouts);
    }

    @Operation(summary = "通过ID获取关于我们页面图片", description = "返回指定ID的关于我们页面图片")
    @GetMapping("/getAboutById/{id}")
    public BaseResponse<About> getAboutById(@Parameter(description = "图片ID", required = true) @PathVariable int id) {
        About about = aboutService.getAboutById(id);
        log.info("通过ID获取关于我们页面图片: {}", about);
        return BaseResponse.success(about);
    }

    @Operation(summary = "新增关于我们页面图片", description = "通过JSON数据新增关于我们页面图片")
    @PostMapping("/addAbout")
    public BaseResponse<String> addAbout(@Parameter(description = "关于我们页面图片数据", required = true) @RequestBody About about) {
        try {
            aboutService.addAbout(about);
            log.info("新增关于我们页面图片: {}", about);
            return BaseResponse.success("关于我们页面图片添加成功");
        } catch (IllegalArgumentException e) {
            log.warn("新增关于我们页面图片失败: {}", e.getMessage());
            return (BaseResponse<String>) BaseResponse.error(0, e.getMessage());
        }
    }

    @Operation(summary = "更新关于我们页面图片", description = "通过JSON数据更新关于我们页面图片")
    @PutMapping("/updateAbout")
    public BaseResponse<String> updateAbout(@Parameter(description = "关于我们页面图片数据", required = true) @RequestBody About about) {
        int result = aboutService.updateAbout(about);
        if (result > 0) {
            log.info("更新关于我们页面图片: {}", about);
            return BaseResponse.success("关于我们页面图片更新成功");
        } else {
            log.warn("更新关于我们页面图片失败: {}", about);
            return (BaseResponse<String>) BaseResponse.error(0, "关于我们页面图片更新失败");
        }
    }

    @Operation(summary = "删除关于我们页面图片", description = "通过图片ID删除关于我们页面图片")
    @DeleteMapping("/deleteAbout/{id}")
    public BaseResponse<String> deleteAbout(@Parameter(description = "图片ID", required = true) @PathVariable int id) {
        aboutService.deleteAbout(id);
        log.info("删除关于我们页面图片: 图片ID={}", id);
        return BaseResponse.success("关于我们页面图片删除成功");
    }

    @Operation(summary = "分页查询关于我们页面图片", description = "根据页码和每页显示条数进行分页查询关于我们页面图片")
    @GetMapping("/getAboutsPaged")
    public BaseResponse<Map<String, Object>> getAboutsPaged(@RequestParam int page, @RequestParam int size, @RequestParam String sortField) {
        Map<String, Object> result = aboutService.getAboutsPaged(page, size, sortField);
        log.info("分页查询关于我们页面图片: {}", result);
        return BaseResponse.success(result);
    }
}
package com.cows.controller.admin;

import com.cows.commons.api.BaseResponse;
import com.cows.entity.BasicInformation;
import com.cows.service.BasicInformationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 基本信息的接口
 */
@Slf4j
@RestController
@RequestMapping("/v1/basicInformation")
public class BasicInformationController {

    @Autowired
    private BasicInformationService basicInformationService;

    @Operation(summary = "获取所有基本信息", description = "返回所有基本信息的列表")
    @GetMapping("/getAllBasicInformation")
    public BaseResponse<List<BasicInformation>> getAllBasicInformation() {
        List<BasicInformation> basicInformationList = basicInformationService.getAllBasicInformation();
        log.info("获取所有基本信息: {}", basicInformationList);
        return BaseResponse.success(basicInformationList);
    }

    @Operation(summary = "通过ID获取基本信息", description = "返回指定ID的基本信息")
    @GetMapping("/getBasicInformationById/{id}")
    public BaseResponse<BasicInformation> getBasicInformationById(@Parameter(description = "基本信息ID", required = true) @PathVariable Long id) {
        BasicInformation basicInformation = basicInformationService.getBasicInformationById(id);
        log.info("通过ID获取基本信息: {}", basicInformation);
            return BaseResponse.success(basicInformation);
        }

    @Operation(summary = "新增基本信息", description = "通过JSON数据新增基本信息")
    @PostMapping("/addBasicInformation")
    public BaseResponse<String> addBasicInformation(@Parameter(description = "基本信息数据", required = true) @RequestBody BasicInformation basicInformation) {
        try {
            basicInformationService.addBasicInformation(basicInformation);
            log.info("新增基本信息: {}", basicInformation);
            return BaseResponse.success("基本信息添加成功");
        } catch (IllegalArgumentException e) {
            log.warn("新增基本信息失败: {}", e.getMessage());
            return (BaseResponse<String>) BaseResponse.error(0, e.getMessage());
        }
    }

    @Operation(summary = "更新基本信息", description = "通过JSON数据更新基本信息")
    @PutMapping("/updateBasicInformation")
    public BaseResponse<String> updateBasicInformation(@Parameter(description = "基本信息数据", required = true) @RequestBody BasicInformation basicInformation) {
        int result = basicInformationService.updateBasicInformation(basicInformation);
        if (result > 0) {
            log.info("更新基本信息: {}", basicInformation);
            return BaseResponse.success("基本信息更新成功");
        } else {
            log.warn("更新基本信息失败: {}", basicInformation);
            return (BaseResponse<String>) BaseResponse.error(0, "基本信息更新失败");
        }
    }

    @Operation(summary = "删除基本信息", description = "通过基本信息ID删除基本信息")
    @DeleteMapping("/deleteBasicInformation/{id}")
    public BaseResponse<String> deleteBasicInformation(@Parameter(description = "基本信息ID", required = true) @PathVariable Long id) {
        basicInformationService.deleteBasicInformation(id);
        log.info("删除基本信息: 基本信息ID={}", id);
        return BaseResponse.success("基本信息删除成功");
    }

    @Operation(summary = "分页查询基本信息", description = "根据页码和每页显示条数进行分页查询基本信息")
    @GetMapping("/getAllBasicInformationPagedSorted")
    public BaseResponse<List<BasicInformation>> getAllBasicInformationPagedSorted(@RequestParam int page, @RequestParam int size, @RequestParam String sortField) {
        List<BasicInformation> basicInformationList = basicInformationService.getAllBasicInformation(page, size, sortField);
        log.info("分页查询基本信息: {}", basicInformationList);
        return BaseResponse.success(basicInformationList);
    }
}
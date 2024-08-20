package com.cows.controller.common;

import com.cows.commons.api.BaseResponse;
import com.cows.entity.Business;
import com.cows.service.BusinessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/businesses")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @Operation(summary = "获取所有招商加盟信息", description = "返回所有招商加盟的列表")
    @GetMapping("/getAllBusinesses")
    public BaseResponse<List<Business>> getAllBusinesses() {
        List<Business> businesses = businessService.getAllBusinesses();
        log.info("获取所有招商加盟信息成功");
        return BaseResponse.success(businesses);
    }

    @Operation(summary = "通过ID获取招商加盟信息", description = "返回指定ID的招商加盟")
    @GetMapping("/getBusinessById/{id}")
    public BaseResponse<Business> getBusinessById(@Parameter(description = "招商加盟ID", required = true) @PathVariable int id) {
        Business business = businessService.getBusinessById(id);
        log.info("通过ID获取招商加盟信息: {}", business);
        return BaseResponse.success(business);
    }

    @Operation(summary = "新增招商加盟", description = "通过JSON数据新增招商加盟")
    @PostMapping("/addBusiness")
    public BaseResponse<String> addBusiness(@Parameter(description = "招商加盟数据", required = true) @RequestBody Business business) {
        try {
            businessService.addBusiness(business);
            log.info("新增招商加盟: {}", business);
            return BaseResponse.success("招商加盟添加成功");
        } catch (IllegalArgumentException e) {
            log.warn("新增招商加盟失败: {}", e.getMessage());
            return (BaseResponse<String>) BaseResponse.error(0, e.getMessage());
        }
    }

    @Operation(summary = "更新招商加盟", description = "通过JSON数据更新招商加盟")
    @PutMapping("/updateBusiness")
    public BaseResponse<String> updateBusiness(@Parameter(description = "招商加盟数据", required = true) @RequestBody Business business) {
        int result = businessService.updateBusiness(business);
        if (result > 0) {
            log.info("更新招商加盟: {}", business);
            return BaseResponse.success("招商加盟更新成功");
        } else {
            log.warn("更新招商加盟失败: {}", business);
            return (BaseResponse<String>) BaseResponse.error(0, "招商加盟更新失败");
        }
    }

    @Operation(summary = "删除招商加盟", description = "通过招商加盟ID删除招商加盟")
    @DeleteMapping("/deleteBusiness/{id}")
    public BaseResponse<String> deleteBusiness(@Parameter(description = "招商加盟ID", required = true) @PathVariable int id) {
        businessService.deleteBusiness(id);
        log.info("删除招商加盟: 招商加盟ID={}", id);
        return BaseResponse.success("招商加盟删除成功");
    }

    @Operation(summary = "分页查询招商加盟", description = "根据页码和每页显示条数进行分页查询招商加盟")
    @GetMapping("/getBusinessesPaged")
    public BaseResponse<Map<String, Object>> getBusinessesPaged(@RequestParam int page, @RequestParam int size, @RequestParam String sortField) {
        Map<String, Object> result = businessService.getBusinessesPaged(page, size, sortField);
        log.info("分页查询招商加盟: {}", result);
        return BaseResponse.success(result);
    }

    @Operation(summary = "搜索招商加盟", description = "通过招商加盟名称搜索招商加盟")
    @GetMapping("/searchBusinessesByName")
    public BaseResponse<List<Business>> searchBusinessesByName(@RequestParam String name) {
        List<Business> businesses = businessService.searchBusinessesByName(name);
        log.info("搜索招商加盟名称: {}", name);
        return BaseResponse.success(businesses);
    }
}
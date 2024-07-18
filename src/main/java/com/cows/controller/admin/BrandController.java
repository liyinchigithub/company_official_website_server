package com.cows.controller.admin;

import com.cows.commons.api.BaseResponse;
import com.cows.entity.Brand;
import com.cows.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Operation(summary = "获取所有品牌信息", description = "返回所有品牌的列表")
    @GetMapping("/getAllBrands")
    public BaseResponse<List<Brand>> getAllBrands() {
        List<Brand> brands = brandService.getAllBrands();
        log.info("获取所有品牌信息成功");
        return BaseResponse.success(brands);
    }

    @Operation(summary = "通过ID获取品牌信息", description = "返回指定ID的品牌")
    @GetMapping("/getBrandById/{id}")
    public BaseResponse<Brand> getBrandById(@Parameter(description = "品牌ID", required = true) @PathVariable int id) {
        Brand brand = brandService.getBrandById(id);
        log.info("通过ID获取品牌信息: {}", brand);
        return BaseResponse.success(brand);
    }

    @Operation(summary = "新增品牌", description = "通过JSON数据新增品牌")
    @PostMapping("/addBrand")
    public BaseResponse<String> addBrand(@Parameter(description = "品牌数据", required = true) @RequestBody Brand brand) {
        try {
            brandService.addBrand(brand);
            log.info("新增品牌: {}", brand);
            return BaseResponse.success("品牌添加成功");
        } catch (IllegalArgumentException e) {
            log.warn("新增品牌失败: {}", e.getMessage());
            return (BaseResponse<String>) BaseResponse.error(0, e.getMessage());
        }
    }

    @Operation(summary = "更新品牌", description = "通过JSON数据更新品牌")
    @PutMapping("/updateBrand")
    public BaseResponse<String> updateBrand(@Parameter(description = "品牌数据", required = true) @RequestBody Brand brand) {
        int result = brandService.updateBrand(brand);
        if (result > 0) {
            log.info("更新品牌: {}", brand);
            return BaseResponse.success("品牌更新成功");
        } else {
            log.warn("更新品牌失败: {}", brand);
            return (BaseResponse<String>) BaseResponse.error(0, "品牌更新失败");
        }
    }

    @Operation(summary = "删除品牌", description = "通过品牌ID删除品牌")
    @DeleteMapping("/deleteBrand/{id}")
    public BaseResponse<String> deleteBrand(@Parameter(description = "品牌ID", required = true) @PathVariable int id) {
        brandService.deleteBrand(id);
        log.info("删除品牌: 品牌ID={}", id);
        return BaseResponse.success("品牌删除成功");
    }

    @Operation(summary = "分页查询品牌", description = "根据页码和每页显示条数进行分页查询品牌")
    @GetMapping("/")
    public BaseResponse<List<Brand>> getBrandsPaged(@RequestParam int page, @RequestParam int size, @RequestParam String sortField) {
        List<Brand> brands = brandService.getBrandsPaged(page, size, sortField);
        log.info("分页查询品牌: {}", brands);
        return BaseResponse.success(brands);
    }

    @Operation(summary = "搜索品牌", description = "根据品牌名称搜索品牌")
    @GetMapping("/search")
    public BaseResponse<List<Brand>> searchBrandsByName(@RequestParam String name) {
        List<Brand> brands = brandService.searchBrandsByName(name);
        log.info("搜索品牌: {}", brands);
        return BaseResponse.success(brands);
}
}
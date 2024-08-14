package com.cows.controller.common;

import com.cows.commons.api.BaseResponse;
import com.cows.entity.ProductsCarousels;
import com.cows.service.ProductsCarouselsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/productsCarousels")
public class ProductsCarouselsController {

    @Autowired
    private ProductsCarouselsService productsCarouselsService;

    @Operation(summary = "获取所有横向轮播图商品配置", description = "返回所有横向轮播图商品配置的列表")
    @GetMapping("/getAllProductsCarousels")
    public BaseResponse<List<ProductsCarousels>> getAllProductsCarousels() {
        List<ProductsCarousels> productsCarousels = productsCarouselsService.getAllProductsCarousels();
        log.info("获取所有横向轮播图商品配置: {}", productsCarousels);
        return BaseResponse.success(productsCarousels);
    }

    @Operation(summary = "通过ID获取横向轮播图商品配置", description = "返回指定ID的横向轮播图商品配置")
    @GetMapping("/getProductsCarouselsById/{id}")
    public BaseResponse<ProductsCarousels> getProductsCarouselsById(@Parameter(description = "配置ID", required = true) @PathVariable int id) {
        ProductsCarousels productsCarousels = productsCarouselsService.getProductsCarouselsById(id);
        log.info("通过ID获取横向轮播图商品配置: {}", productsCarousels);
        System.out.println("Retrieved from DB: " + productsCarousels);
        return BaseResponse.success(productsCarousels);
    }

    @Operation(summary = "新增横向轮播图商品配置", description = "通过JSON数据新增横向轮播图商品配置")
    @PostMapping("/addProductsCarousels")
    public BaseResponse<String> addProductsCarousels(@Parameter(description = "横向轮播图商品配置数据", required = true) @RequestBody ProductsCarousels productsCarousels) {
        try {
            productsCarouselsService.addProductsCarousels(productsCarousels);
            log.info("新增横向轮播图商品配置: {}", productsCarousels);
            return BaseResponse.success("横向轮播图商品配置添加成功");
        } catch (IllegalArgumentException e) {
            log.warn("新增横向轮播图商品配置失败: {}", e.getMessage());
            return (BaseResponse<String>) BaseResponse.error(0, e.getMessage());
        }
    }

    @Operation(summary = "更新横向轮播图商品配置", description = "通过JSON数据更新横向轮播图商品配置")
    @PutMapping("/updateProductsCarousels")
    public BaseResponse<String> updateProductsCarousels(@Parameter(description = "横向轮播图商品配置数据", required = true) @RequestBody ProductsCarousels productsCarousels) {
        int result = productsCarouselsService.updateProductsCarousels(productsCarousels);
        if (result > 0) {
            log.info("更新横向轮播图商品配置: {}", productsCarousels);
            return BaseResponse.success("横向轮播图商品配置更新成功");
        } else {
            log.warn("更新横向轮播图商品配置失败: {}", productsCarousels);
            return (BaseResponse<String>) BaseResponse.error(0, "横向轮播图商品配置更新失败");
        }
    }

    @Operation(summary = "删除横向轮播图商品配置", description = "通过配置ID删除横向轮播图商品配置")
    @DeleteMapping("/deleteProductsCarousels/{id}")
    public BaseResponse<String> deleteProductsCarousels(@Parameter(description = "配置ID", required = true) @PathVariable int id) {
        productsCarouselsService.deleteProductsCarousels(id);
        log.info("删除横向轮播图商品配置: 配置ID={}", id);
        return BaseResponse.success("横向轮播图商品配置删除成功");
    }
}
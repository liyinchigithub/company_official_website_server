package com.cows.controller.common;

import com.cows.commons.api.BaseResponse;
import com.cows.entity.ProductCategory;
import com.cows.service.ProductCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/productCategories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Operation(summary = "获取所有商品分类信息", description = "返回所有商品分类的列表")
    @GetMapping("/getAllProductCategories")
    public BaseResponse<List<ProductCategory>> getAllProductCategories() {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        log.info("获取所有商品分类信息: {}", productCategories);
        return BaseResponse.success(productCategories);
    }

    @Operation(summary = "通过ID获取商品分类信息",     description = "返回指定ID的商品分类")
    @GetMapping("/getProductCategoryById/{id}")
    public BaseResponse<ProductCategory> getProductCategoryById(@Parameter(description = "商品分类ID", required = true) @PathVariable int id) {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        log.info("通过ID获取商品分类信息: {}", productCategory);
        return BaseResponse.success(productCategory);
    }

    @Operation(summary = "新增商品分类", description = "通过JSON数据新增商品分类")
    @PostMapping("/addProductCategory")
    public BaseResponse<String> addProductCategory(@Parameter(description = "商品分类数据", required = true) @RequestBody ProductCategory productCategory) {
        try {
            productCategoryService.addProductCategory(productCategory);
            log.info("新增商品分类: {}", productCategory);
            return BaseResponse.success("商品分类添加成功");
        } catch (IllegalArgumentException e) {
            log.warn("新增商品分类失败: {}", e.getMessage());
            return (BaseResponse<String>) BaseResponse.error(0, e.getMessage());
        }
    }

    @Operation(summary = "更新商品分类", description = "通过JSON数据更新商品分类")
    @PutMapping("/updateProductCategory")
    public BaseResponse<String> updateProductCategory(@Parameter(description = "商品分类数据", required = true) @RequestBody ProductCategory productCategory) {
        int result = productCategoryService.updateProductCategory(productCategory);
        if (result > 0) {
            log.info("更新商品分类: {}", productCategory);
            return BaseResponse.success("商品分类更新成功");
        } else {
            log.warn("更新商品分类失败: {}", productCategory);
            return (BaseResponse<String>) BaseResponse.error(0, "商品分类更新失败");
        }
    }

    @Operation(summary = "删除商品分类", description = "通过商品分类ID删除商品分类")
    @DeleteMapping("/deleteProductCategory/{id}")
    public BaseResponse<String> deleteProductCategory(@Parameter(description = "商品分类ID", required = true) @PathVariable int id) {
        productCategoryService.deleteProductCategory(id);
        log.info("删除商品分类: 商品分类ID={}", id);
        return BaseResponse.success("商品分类删除成功");
    }

    @Operation(summary = "分页查询商品分类", description = "根据页码和每页显示条数进行分页查询商品分类")
    @GetMapping("/getProductCategoriesPaged")
    public BaseResponse<List<ProductCategory>> getProductCategoriesPaged(@RequestParam int page, @RequestParam int size, @RequestParam String sortField) {
        List<ProductCategory> productCategories = productCategoryService.getProductCategoriesPaged(page, size, sortField);
        log.info("分页查询商品分类: {}", productCategories);
        return BaseResponse.success(productCategories);
    }
}
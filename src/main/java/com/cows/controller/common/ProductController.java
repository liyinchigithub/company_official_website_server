package com.cows.controller.common;

import com.cows.commons.api.BaseResponse;
import com.cows.entity.Product;
import com.cows.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "获取所有商品信息", description = "返回所有商品的列表")
    @GetMapping("/getAllProducts")
    public BaseResponse<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        log.info("获取所有商品信息成功");
        return BaseResponse.success(products);
    }

@Operation(summary = "通过ID获取商品信息", description = "返回指定ID的商品")
    @GetMapping("/getProductById/{id}")
    public BaseResponse<Product> getProductById(@Parameter(description = "商品ID", required = true) @PathVariable int id) {
        Product product = productService.getProductById(id);
        log.info("通过ID获取商品信息: {}", product);
        return BaseResponse.success(product);
    }

    @Operation(summary = "新增商品", description = "通过JSON数据新增商品")
    @PostMapping("/addProduct")
    public BaseResponse<String> addProduct(@Parameter(description = "商品数据", required = true) @RequestBody Product product) {
        try {
            productService.addProduct(product);
            log.info("新增商品: {}", product);
            return BaseResponse.success("商品添加成功");
        } catch (IllegalArgumentException e) {
            log.warn("新增商品失败: {}", e.getMessage());
            return (BaseResponse<String>) BaseResponse.error(0, e.getMessage());
        }
    }

    @Operation(summary = "更新商品", description = "通过JSON数据更新商品")
    @PutMapping("/updateProduct")
    public BaseResponse<String> updateProduct(@Parameter(description = "商品数据", required = true) @RequestBody Product product) {
        int result = productService.updateProduct(product);
        if (result > 0) {
            log.info("更新商品: {}", product);
            return BaseResponse.success("商品更新成功");
        } else {
            log.warn("更新商品失败: {}", product);
            return (BaseResponse<String>) BaseResponse.error(0, "商品更新失败");
        }
    }

    @Operation(summary = "删除商品", description = "通过商品ID删除商品")
    @DeleteMapping("/deleteProduct/{id}")
    public BaseResponse<String> deleteProduct(@Parameter(description = "商品ID", required = true) @PathVariable int id) {
        productService.deleteProduct(id);
        log.info("删除商品: 商品ID={}", id);
        return BaseResponse.success("商品删除成功");
    }

    @Operation(summary = "分页查询商品", description = "根据页码和每页显示条数进行分页查询商品")
    @GetMapping("/getProductsPaged")
    public BaseResponse<Map<String, Object>> getProductsPaged(@RequestParam int page, @RequestParam int size, @RequestParam String sortField) {
        Map<String, Object> result = productService.getProductsPaged(page, size, sortField);
        log.info("分页查询商品: {}", result);
        return BaseResponse.success(result);
    }

    @Operation(summary = "搜索商品", description = "通过商品名称搜索商品")
    @GetMapping("/searchProductsByName")
    public BaseResponse<List<Product>> searchProductsByName(@RequestParam String name) {
        List<Product> products = productService.searchProductsByName(name);
        log.info("搜索商品名称: {}", name);
        return BaseResponse.success(products);
    }
}
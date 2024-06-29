package com.cows.controller.mobile;

import com.cows.commons.api.BaseResponse;
import com.cows.entity.Carousel;
import com.cows.service.CarouselService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 轮播图的接口
 */
@Slf4j
@RestController
@RequestMapping("/v1/carousels")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    @Operation(summary = "获取所有轮播图信息", description = "返回所有轮播图的列表")
    @GetMapping("/getAllCarousels")
    public BaseResponse<List<Carousel>> getAllCarousels() {
        List<Carousel> carousels = carouselService.getAllCarousels();
        log.info("获取所有轮播图信息: {}", carousels);
        return BaseResponse.success(carousels);
    }

    @Operation(summary = "通过ID获取轮播图信息", description = "返回指定ID的轮播图")
    @GetMapping("/getCarouselById/{id}")
    public BaseResponse<Carousel> getCarouselById(@Parameter(description = "轮播图ID", required = true) @PathVariable int id) {
        Carousel carousel = carouselService.getCarouselById(id);
        log.info("通过ID获取轮播图信息: {}", carousel);
        return BaseResponse.success(carousel);
    }

    @Operation(summary = "新增轮播图", description = "通过JSON数据新增轮播图")
    @PostMapping("/addCarousel")
    public BaseResponse<String> addCarousel(@Parameter(description = "轮播图数据", required = true) @RequestBody Carousel carousel) {
        try {
            carouselService.addCarousel(carousel);
            log.info("新增轮播图: {}", carousel);
            return BaseResponse.success("轮播图添加成功");
        } catch (IllegalArgumentException e) {
            log.warn("新增轮播图失败: {}", e.getMessage());
            return (BaseResponse<String>) BaseResponse.error(0, e.getMessage());
        }
    }

    @Operation(summary = "更新轮播图", description = "通过JSON数据更新轮播图")
    @PutMapping("/updateCarousel")
    public BaseResponse<String> updateCarousel(@Parameter(description = "轮播图数据", required = true) @RequestBody Carousel carousel) {
        carouselService.updateCarousel(carousel);
        log.info("更新轮播图: {}", carousel);
        return BaseResponse.success("轮播图更新成功");
    }

    @Operation(summary = "删除轮播图", description = "通过轮播图ID删除轮播图")
    @DeleteMapping("/deleteCarousel/{id}")
    public BaseResponse<String> deleteCarousel(@Parameter(description = "轮播图ID", required = true) @PathVariable int id) {
        carouselService.deleteCarousel(id);
        log.info("删除轮播图: 轮播图ID={}", id);
        return BaseResponse.success("轮播图删除成功");
    }

    @Operation(summary = "分页查询轮播图", description = "根据页码和每页显示条数进行分页查询轮播图")
    @GetMapping("/getAllCarouselsPagedSorted")
    public BaseResponse<List<Carousel>> getAllCarouselsPagedSorted(@RequestParam int page, @RequestParam int size, @RequestParam String sortField) {
        List<Carousel> carousels = carouselService.getAllCarousels(page, size, sortField);
        log.info("分页查询轮播图: {}", carousels);
        return BaseResponse.success(carousels);
    }
}
package com.cows.controller.common;

import com.cows.commons.api.BaseResponse;
import com.cows.entity.Order;
import com.cows.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 一个控制器类，用于处理订单相关的请求。
 * 控制器->服务层->数据访问层
 * @author liyinchi
 * @date 2024/06/29
 */
@Slf4j
@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 获取所有订单信息
     */
    @Operation(summary = "获取所有订单信息", description = "返回所有订单的列表")
    @GetMapping("/getAllOrders")
    public BaseResponse<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        log.info("获取所有订单信息: {}", orders);
        return BaseResponse.success(orders);
    }

    /**
     * 通过ID获取订单信息
     */
    @Operation(summary = "通过ID获取订单信息", description = "返回指定ID的订单")
    @GetMapping("/getOrderById/{id}")
    public BaseResponse<Order> getOrderById(@Parameter(description = "订单ID", required = true) @PathVariable int id) {
        Order order = orderService.getOrderById(id);
        log.info("通过ID获取订单信息: {}", order);
        return BaseResponse.success(order);
    }

    /**
     * 新增订单
     */
    @Operation(summary = "新增订单", description = "通过JSON数据新增订单")
    @PostMapping("/addOrder")
    public BaseResponse<String> addOrder(@Parameter(description = "订单数据", required = true) @RequestBody Order order) {
        orderService.addOrder(order);
        log.info("新增订单: {}", order);
        return BaseResponse.success("订单添加成功");
    }

    /**
     * 更新订单
     */
    @Operation(summary = "更新订单", description = "通过JSON数据更新订单")
    @PutMapping("/updateOrder")
    public BaseResponse<String> updateOrder(@Parameter(description = "订单数据", required = true) @RequestBody Order order) {
        orderService.updateOrder(order);
        log.info("更新订单: {}", order);
        return BaseResponse.success("订单更新成功");
    }

    /**
     * 删除订单
     */
    @Operation(summary = "删除订单", description = "通过订单ID删除订单")
    @DeleteMapping("/deleteOrder/{id}")
    public BaseResponse<String> deleteOrder(@Parameter(description = "订单ID", required = true) @PathVariable int id) {
        orderService.deleteOrder(id);
        log.info("删除订单: 订单ID={}", id);
        return BaseResponse.success("订单删除成功");
    }

    /**
     * 分页查询订单
     */
    @Operation(summary = "分页查询订单", description = "根据页码和每页显示条数进行分页查询订单")
    @GetMapping("/getAllOrdersPagedSorted")
    public BaseResponse<List<Order>> getAllOrdersPagedSorted(@RequestParam int page, @RequestParam int size, @RequestParam String sortField) {
        List<Order> orders = orderService.getAllOrders(page, size, sortField);
        log.info("分页查询订单: {}", orders);
        return BaseResponse.success(orders);
    }

}
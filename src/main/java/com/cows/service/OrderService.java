package com.cows.service;

import com.cows.entity.Order;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * OrderService 接口
 * 层级：业务逻辑层（Service）、接口层（API）
 * 作用：定义了订单相关的业务方法，代表了应用程序中处理订单的业务操作
 */
public interface OrderService {
    // 获取所有订单
    List<Order> getAllOrders();
    // 通过id获取订单
    Order getOrderById(int id);
    // 添加订单
    int addOrder(Order order);
    // 更新订单
    int updateOrder(Order order);
    // 删除订单
    int deleteOrder(int id);
    // 插入订单
    @Transactional // 在Spring事务管理器中执行。确保在方法执行过程中，如果发生异常，可以进行事务回滚，从而保证数据的一致性。
    void insertOrder(Order order);
    // 分页查询
    List<Order> getAllOrders(int page, int size, String sortField);
}
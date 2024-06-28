package com.cows.mapper;

import com.cows.entity.Order;

import java.util.List;

/**
 * 定义接口（即"数据库访问对象"）
 * 层级：接口层
 * 通常来说，接口中的方法与数据库中的表字段一一对应
 * 接口中定义的方法，需要与Mapper.xml中的SQL语句相对应
 *
 * */
public interface OrderMapper {
    List<Order> findAllOrders(); // 返回 list对象

    Order findOrderById(int id);// 返回 类对象

    int insertOrder(Order order);// 插入

    int updateOrder(Order order);// 更新

    int deleteOrder(int id);// 删除

    List<Order> getAllOrders(int offset, int limit, String sortField);// 分页查询
}

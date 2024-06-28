package com.cows.serviceImpl;

import com.cows.entity.Order;
import com.cows.mapper.OrderMapper;
import com.cows.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Order> getAllOrders() {
        return orderMapper.findAllOrders();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order getOrderById(int id) {
        return orderMapper.findOrderById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addOrder(Order order) {
        if (order.getTotalPrice() == null) {
            throw new IllegalArgumentException("Total price cannot be null");
        }
        orderMapper.insertOrder(order);
        return order.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateOrder(Order order) {
        int updates = orderMapper.updateOrder(order);
        return updates;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteOrder(int id) {
        int updates = orderMapper.deleteOrder(id);
        return updates;
    }

    @Override
    @Transactional
    public void insertOrder(Order order) {
        orderMapper.insertOrder(order);
        throw new RuntimeException(); // 手动抛出异常以测试事务回滚
    }

    @Override
    public List<Order> getAllOrders(int page, int size, String sortField) {
        log.debug("page" + page + " size" + size + " sortField" + sortField);
        // 检查page和size参数的有效性
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page must be non-negative and size must be positive");
        }

        // 检查sortField参数的有效性
        if (sortField == null || sortField.isEmpty()) {
            sortField = "id"; // 使用一个默认的排序字段
        }

        int offset = page * size;
        return orderMapper.getAllOrders(offset, size, sortField);
    }
}
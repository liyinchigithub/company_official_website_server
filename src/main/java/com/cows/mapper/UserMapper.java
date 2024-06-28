package com.cows.mapper;

import com.cows.entity.User;

import java.util.List;

/**
 * 定义接口（即"数据库访问对象"）
 * 层级：接口层
 * 通常来说，接口中的方法与数据库中的表字段一一对应
 * 接口中定义的方法，需要与Mapper.xml中的SQL语句相对应
 *
 * */
public interface UserMapper {

    User findByWechatOpenId(String openId);  // 用于查找微信用户
    
    List<User> findAllUsers(); // 返回 list对象

    User findUserById(int id);// 返回 类对象

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(int id);
    // 分页查询
    List<User> getAllUsers(int offset, int limit, String sortField);
}

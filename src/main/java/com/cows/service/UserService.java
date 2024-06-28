package com.cows.service;

import com.cows.entity.User;
import com.cows.entity.WechatUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserService 接口
 * 层级：业务逻辑层（Service）、接口层（API）
 * 作用：定义了业务方法，它的方法代表了你的应用程序中的业务操作
 *
 */
public interface UserService {
    List<User> getAllUsers();

    User getUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);

    @Transactional // 在Spring事务管理器中执行。确保在方法执行过程中，如果发生异常，可以进行事务回滚，从而保证数据的一致性。
    void insertUser(User user);
    // 分页查询
    List<User> getAllUsers(int page, int size, String sortField);

    User findByWechatOpenId(String openId);  // 通过微信OpenId查找用户

    User loginOrCreateWechatUser(WechatUser wechatUser);// 登录或创建微信用户
}

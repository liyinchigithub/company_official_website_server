package com.cows.serviceImpl;

import com.cows.entity.User;
import com.cows.entity.WechatUser;
import com.cows.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.cows.mapper.UserMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserService接口的实现类
 * 层级：Service > Mapper > Dao
 * 作用：
 * 通常在这边，通过调用数据对象（UserMapper.java）中的方法，实现对数据库的操作。
 * UserService的实现（例如UserServiceImpl）通常会使用UserMapper来访问数据库，但它也可能包含其他的业务逻辑，例如验证、错误处理、事务管理等
 *
 * */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserMapper userMapper;// 使用@Autowired注解自动注入UserMapper实例

    @Autowired
    private PasswordEncoder passwordEncoder;  // 自动注入PasswordEncoder
    
    @Resource
    private ApplicationContext applicationContext;// 使用ApplicationContext来访问Spring容器中的Bean和资源，如数据库连接、消息服务、定时任务等。通过注入ApplicationContext实例，可以方便地在代码中使用这些资源。

    /**
     * 构造函数
     * 通过构造函数，将数据对象（UserMapper interface）注入到UserServiceImpl中
     * */
    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 获取所有用户
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<User> getAllUsers() {
        return userMapper.findAllUsers();
    }

    // 通过id查找用户
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User getUserById(int id) {
        return userMapper.findUserById(id);
    }
    // 添加用户
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUser(User user) {
        // 加密密码
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);// 设置加密后的密码
        userMapper.insertUser(user);
        return user.getId(); // 返回新生成的用户ID
    }
    // 更新用户
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(User user) {
        log.info("Updating user: {}", user);  // 添加日志
        int updates = userMapper.updateUser(user);
        // 返回的执行结果  1成功 0
        return updates;
    }
    // 删除用户
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUser(int id) {
        int updates = userMapper.deleteUser(id);
        // 返回的执行结果 1成功 0
        return updates;
    }

    // 插入用户
    @Override
    @Transactional
    public void insertUser(User user) {
        // 插入用户信息
        userMapper.insertUser(user);
        // 手动抛出异常
        throw new RuntimeException();// 当调用此方法时，会抛出异常，并回滚事务，不会插入数据
    }

    // 获取所有用户
    @Override
    public List<User> getAllUsers(int page, int size, String sortField) {
        log.debug("page" + page + " size" + size + " sortField" + sortField);
        // 检查page和size参数的有效性
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page must be non-negative and size must be positive");
        }

        // 检查sortField参数的有效性
        if (sortField == null || sortField.isEmpty()) {
            sortField = "id";  // 使用一个默认的排序字段
        }

        int offset = page * size;
        return userMapper.getAllUsers(offset, size, sortField);
    }

    // 通过微信openid查找用户
    @Override
    public User findByWechatOpenId(String openId) {
        return userMapper.findByWechatOpenId(openId);
    }

    // 登录或创建微信用户
    @Override
    public User loginOrCreateWechatUser(WechatUser wechatUser) {
        User user = userMapper.findByWechatOpenId(wechatUser.getOpenId());
        if (user == null) {
            user = new User();
            System.out.println("创建微信用户");
            log.info("wechatUser.getOpenId()："+wechatUser.getOpenId());
            // 设置其他必要属性，例如用户名等
            user.setWechatOpenId(wechatUser.getOpenId());
            // 随机字母+数字
            String randomString = RandomStringUtils.randomAlphanumeric(10);
            user.setUserName(randomString); // 示例：设置默认用户名，实际应用中应根据实际情况设置
            user.setPassword("123456"); // 示例：设置默认密码，实际应用中应根据实际情况设置
            user.setDeleted(false);
            user.setEnable(true);
            userMapper.insertUser(user);
            // 确保新插入的用户ID被设置
            user = userMapper.findByWechatOpenId(wechatUser.getOpenId());
            // 判断是否插入成功
            if (user == null) {
                throw new RuntimeException("创建微信用户失败");
            }
            // 调试输出
            System.out.println("创建微信用户成功");
        } else {
            System.out.println("已有微信用户，不创建，登录微信用户");
        }
        return user;
    }

    /**
     * 发布事件
     * @return
     */
//    public User getUser2() {
//        // TODO 数据库查询
//        User user = new User(1, "liyinchi", "123456", 0);
//        // 发布事件
//        MyEvent event = new MyEvent(this, user);
//        applicationContext.publishEvent(event);
//        return user;
//    }


}
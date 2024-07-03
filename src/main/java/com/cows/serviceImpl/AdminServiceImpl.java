package com.cows.serviceImpl;

import com.cows.entity.Admin;
import com.cows.mapper.AdminMapper;
import com.cows.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Admin login(String userName, String password) {
        Admin admin = adminMapper.findAdminByUserName(userName);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }

    @Override
    @Transactional
    public void logout() {
        // 这里可以添加任何需要的业务逻辑，例如记录日志
        log.info("管理员退出登录");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Admin> getAllAdmins() {
        return adminMapper.findAllAdmins();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Admin getAdminById(int id) {
        return adminMapper.findAdminById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addAdmin(Admin admin) {
        if (adminMapper.countByUserName(admin.getUserName()) > 0) {
            throw new IllegalArgumentException("用户名已存在");
        }
        adminMapper.insertAdmin(admin);
        return admin.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAdmin(Admin admin) {
        return adminMapper.updateAdmin(admin);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAdmin(int id) {
        return adminMapper.deleteAdmin(id);
    }

    @Override
    @Transactional
    public void insertAdmin(Admin admin) {
        adminMapper.insertAdmin(admin);
        throw new RuntimeException(); // 手动抛出异常以测试事务回滚
    }

    @Override
    public List<Admin> getAllAdmins(int page, int size, String sortField) {
        log.debug("page" + page + " size" + size + " sortField" + sortField);
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page must be non-negative and size must be positive");
        }
        if (sortField == null || sortField.isEmpty()) {
            sortField = "id";
        }
        int offset = page * size;
        return adminMapper.getAllAdmins(offset, size, sortField);
    }
}
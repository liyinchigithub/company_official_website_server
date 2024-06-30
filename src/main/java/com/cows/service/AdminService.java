package com.cows.service;

import com.cows.entity.Admin;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface AdminService {
    List<Admin> getAllAdmins();//
    Admin getAdminById(int id);
    int addAdmin(Admin admin);
    int updateAdmin(Admin admin);
    int deleteAdmin(int id);
    @Transactional
    void insertAdmin(Admin admin);
    List<Admin> getAllAdmins(int page, int size, String sortField);
}
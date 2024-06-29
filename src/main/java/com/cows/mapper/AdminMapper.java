package com.cows.mapper;

import com.cows.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface AdminMapper {
    List<Admin> findAllAdmins();
    //   根据id查询管理员
    Admin findAdminById(int id);
    //   插入管理员
    int insertAdmin(Admin admin);
    //  更新管理员信息
    int updateAdmin(Admin admin);
    //  删除管理员，逻辑删除，将isDeleted字段设置为1
    int deleteAdmin(int id);
    List<Admin> getAllAdmins(int offset, int limit, String sortField);
    // 查询是否存在同名用户
    @Select("SELECT COUNT(*) FROM Admins WHERE userName = #{userName}")
    int countByUserName(String userName);
}
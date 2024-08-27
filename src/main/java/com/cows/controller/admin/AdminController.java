package com.cows.controller.admin;

import com.cows.commons.api.BaseResponse;
import com.cows.entity.Admin;
import com.cows.service.AdminService;
import com.cows.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 企业官网的接口
 * 
 * @author liyinchi
 * @date 2024/06/29
 */
@Slf4j
@RestController
@RequestMapping("/v1/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public AdminController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public BaseResponse<Map<String, String>> login(@RequestBody Admin admin) {
        Admin loggedInAdmin = adminService.login(admin.getUserName(), admin.getPassword());
        if (loggedInAdmin != null) {
            String token = JwtUtil.generateToken(admin.getUserName()); // 假设你有一个JwtUtil类来生成token
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return BaseResponse.success(response);
        } else {
            return (BaseResponse<Map<String, String>>) BaseResponse.error(1, "fail");
        }
    }

    @PostMapping("/logout")
    public BaseResponse<String> logout() {
        // 这里可以添加任何需要的业务逻辑，例如记录日志
        log.info("管理员退出登录");
        return BaseResponse.success("退出登录成功");
    }

    @Operation(summary = "获取所有管理员信息", description = "返回所有管理员的列表")
    @GetMapping("/getAllAdmins")
    public BaseResponse<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        log.info("获取所有管理员信息: {}", admins);
        return BaseResponse.success(admins);
    }

    @Operation(summary = "通过ID获取管理员信息", description = "返回指定ID的管理员")
    @GetMapping("/getAdminById/{id}")
    public BaseResponse<Admin> getAdminById(@Parameter(description = "管理员ID", required = true) @PathVariable int id) {
        Admin admin = adminService.getAdminById(id);
        log.info("通过ID获取管理员信息: {}", admin);
        return BaseResponse.success(admin);
    }

    @Operation(summary = "新增管理员", description = "通过JSON数据新增管理员")
    @PostMapping("/addAdmin")
    public BaseResponse<String> addAdmin(@Parameter(description = "管理员数据", required = true) @RequestBody Admin admin) {
        try {
            adminService.addAdmin(admin);
            log.info("新增管理员: {}", admin);
            return BaseResponse.success("管理员添加成功");
        } catch (IllegalArgumentException e) {
            log.warn("新增管理员失败: {}", e.getMessage());
            return (BaseResponse<String>) BaseResponse.error(0, e.getMessage());
        }
    }

    @Operation(summary = "更新管理员", description = "通过JSON数据更新管理员")
    @PutMapping("/updateAdmin")
    public BaseResponse<String> updateAdmin(
            @Parameter(description = "管理员数据", required = true) @RequestBody Admin admin) {
        int result = adminService.updateAdmin(admin);
        if (result > 0) {
            log.info("更新管理员: {}", admin);
            return BaseResponse.success("管理员更新成功");
        } else {
            log.warn("更新管理员失败: {}", admin);
            return (BaseResponse<String>) BaseResponse.error(0, "管理员更新失败");
        }
    }

    @Operation(summary = "删除管理员", description = "通过管理员ID删除管理员")
    @DeleteMapping("/deleteAdmin/{id}")
    public BaseResponse<String> deleteAdmin(@Parameter(description = "管理员ID", required = true) @PathVariable int id) {
        adminService.deleteAdmin(id);
        log.info("删除管理员: 管理员ID={}", id);
        return BaseResponse.success("管理员删除成功");
    }

    @Operation(summary = "分页查询管理员", description = "根据页码和每页显示条数进行分页查询管理员")
    @GetMapping("/getAllAdminsPagedSorted")
    public BaseResponse<List<Admin>> getAllAdminsPagedSorted(@RequestParam int page, @RequestParam int size,
            @RequestParam String sortField) {
        List<Admin> admins = adminService.getAllAdmins(page, size, sortField);
        log.info("分页查询管理员: {}", admins);
        return BaseResponse.success(admins);
    }
}
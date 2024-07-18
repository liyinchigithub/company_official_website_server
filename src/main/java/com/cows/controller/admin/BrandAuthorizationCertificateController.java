package com.cows.controller.admin;

import com.cows.commons.api.BaseResponse;
import com.cows.entity.BrandAuthorizationCertificate;
import com.cows.service.BrandAuthorizationCertificateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/certificates")
public class BrandAuthorizationCertificateController {

    @Autowired
    private BrandAuthorizationCertificateService certificateService;

    @Operation(summary = "获取所有证书信息", description = "返回所有证书的列表")
    @GetMapping("/getAllCertificates")
    public BaseResponse<List<BrandAuthorizationCertificate>> getAllCertificates() {
        List<BrandAuthorizationCertificate> certificates = certificateService.getAllCertificates();
        log.info("获取所有证书信息成功");
        return BaseResponse.success(certificates);
    }

    @Operation(summary = "通过ID获取证书信息", description = "返回指定ID的证书")
    @GetMapping("/getCertificateById/{id}")
    public BaseResponse<BrandAuthorizationCertificate> getCertificateById(
            @Parameter(description = "证书ID", required = true) @PathVariable int id) {
        BrandAuthorizationCertificate certificate = certificateService.getCertificateById(id);
        log.info("通过ID获取证书信息: {}", certificate);
        return BaseResponse.success(certificate);
    }

    @Operation(summary = "新增证书", description = "通过JSON数据新增证书")
    @PostMapping("/addCertificate")
    public BaseResponse<String> addCertificate(
            @Parameter(description = "证书数据", required = true) @RequestBody BrandAuthorizationCertificate certificate) {
        try {
            certificateService.addCertificate(certificate);
            log.info("新增证书: {}", certificate);
            return BaseResponse.success("证书添加成功");
        } catch (IllegalArgumentException e) {
            log.warn("新增证书失败: {}", e.getMessage());
            return (BaseResponse<String>) BaseResponse.error(0, e.getMessage());
        }
    }

    @Operation(summary = "更新证书", description = "通过JSON数据更新证书")
    @PutMapping("/updateCertificate")
    public BaseResponse<String> updateCertificate(
            @Parameter(description = "证书数据", required = true) @RequestBody BrandAuthorizationCertificate certificate) {
        int result = certificateService.updateCertificate(certificate);
        if (result > 0) {
            log.info("更新证书: {}", certificate);
            return BaseResponse.success("证书更新成功");
        } else {
            log.warn("更新证书失败: {}", certificate);
            return (BaseResponse<String>) BaseResponse.error(0, "证书更新失败");
        }
    }

    @Operation(summary = "删除证书", description = "通过证书ID删除证书")
    @DeleteMapping("/deleteCertificate/{id}")
    public BaseResponse<String> deleteCertificate(
            @Parameter(description = "证书ID", required = true) @PathVariable int id) {
        certificateService.deleteCertificate(id);
        log.info("删除证书: 证书ID={}", id);
        return BaseResponse.success("证书删除成功");
    }

    @Operation(summary = "分页查询证书", description = "根据页码和每页显示条数进行分页查询证书")
    @GetMapping("/page")
    public BaseResponse<List<BrandAuthorizationCertificate>> getCertificatesPaged(@RequestParam int page,
            @RequestParam int size, @RequestParam String sortField) {
        List<BrandAuthorizationCertificate> certificates = certificateService.getCertificatesPaged(page, size,
                sortField);
        log.info("分页查询证书: {}", certificates);
        return BaseResponse.success(certificates);
    }

    @Operation(summary = "搜索证书", description = "根据证书名称搜索证书")
    @GetMapping("/search")
    public BaseResponse<List<BrandAuthorizationCertificate>> searchCertificatesByName(@RequestParam String name) {
        List<BrandAuthorizationCertificate> certificates = certificateService.searchCertificatesByName(name);
        log.info("搜索证书: {}", certificates);
        return BaseResponse.success(certificates);
    }
}
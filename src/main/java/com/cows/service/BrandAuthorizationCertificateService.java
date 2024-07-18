package com.cows.service;

import com.cows.entity.BrandAuthorizationCertificate;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 品牌授权书服务接口
 * */
public interface BrandAuthorizationCertificateService {
    List<BrandAuthorizationCertificate> getAllCertificates();
    BrandAuthorizationCertificate getCertificateById(int id);
    int addCertificate(BrandAuthorizationCertificate certificate);
    int updateCertificate(BrandAuthorizationCertificate certificate);
    int deleteCertificate(int id);
    List<BrandAuthorizationCertificate> getCertificatesPaged(int page, int size, String sortField);
    List<BrandAuthorizationCertificate> searchCertificatesByName(String name);

}
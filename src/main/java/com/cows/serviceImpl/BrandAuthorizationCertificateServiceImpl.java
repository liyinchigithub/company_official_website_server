package com.cows.serviceImpl;

import com.cows.entity.BrandAuthorizationCertificate;
import com.cows.mapper.BrandAuthorizationCertificateMapper;
import com.cows.service.BrandAuthorizationCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@Service
public class BrandAuthorizationCertificateServiceImpl implements BrandAuthorizationCertificateService {
    @Autowired
    private BrandAuthorizationCertificateMapper certificateMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<BrandAuthorizationCertificate> getAllCertificates() {
        return certificateMapper.findAllCertificates();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BrandAuthorizationCertificate getCertificateById(int id) {
        return certificateMapper.findCertificateById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addCertificate(BrandAuthorizationCertificate certificate) {
        certificateMapper.insertCertificate(certificate);
        return certificate.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateCertificate(BrandAuthorizationCertificate certificate) {
        return certificateMapper.updateCertificate(certificate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteCertificate(int id) {
        return certificateMapper.deleteCertificate(id);
    }

    @Override
@Transactional(rollbackFor = Exception.class)
public List<BrandAuthorizationCertificate> getCertificatesPaged(int page, int size, String sortField) {
    log.debug("page: {}, size: {}, sortField: {}", page, size, sortField);
    if (page < 0 || size <= 0) {
        throw new IllegalArgumentException("Page must be non-negative and size must be positive");
    }
    if (sortField == null || sortField.isEmpty()) {
        sortField = "id";
    }
    int offset = page * size;
    return certificateMapper.findCertificatesPaged(offset, size, sortField);
}

@Override
@Transactional(rollbackFor = Exception.class)
public List<BrandAuthorizationCertificate> searchCertificatesByName(String name) {
    return certificateMapper.searchCertificatesByName(name);
}
}
package com.cows.mapper;

import com.cows.entity.BrandAuthorizationCertificate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 品牌授权书Mapper接口
 * */
@Mapper
public interface BrandAuthorizationCertificateMapper {
    List<BrandAuthorizationCertificate> findAllCertificates();
    BrandAuthorizationCertificate findCertificateById(int id);
    int insertCertificate(BrandAuthorizationCertificate certificate);
    int updateCertificate(BrandAuthorizationCertificate certificate);
    int deleteCertificate(int id);
    List<BrandAuthorizationCertificate> findCertificatesPaged(@Param("offset") int offset, @Param("limit") int limit, @Param("sortField") String sortField);
    List<BrandAuthorizationCertificate> searchCertificatesByName(@Param("name") String name);

}
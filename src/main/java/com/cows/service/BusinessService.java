package com.cows.service;

import com.cows.entity.Business;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

/**
 * 招商加盟信息服务接口
 * */
public interface BusinessService {
    List<Business> getAllBusinesses();
    Business getBusinessById(int id);
    int addBusiness(Business business);
    int updateBusiness(Business business);
    int deleteBusiness(int id);
    Map<String, Object> getBusinessesPaged(int page, int size, String sortField);
    List<Business> searchBusinessesByName(String name);
}
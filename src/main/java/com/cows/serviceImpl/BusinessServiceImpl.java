package com.cows.serviceImpl;

import com.cows.entity.Business;
import com.cows.mapper.BusinessMapper;
import com.cows.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 招商加盟信息服务接口实现类
 * */
@Slf4j
@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Business> getAllBusinesses() {
        return businessMapper.findAllBusinesses();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Business getBusinessById(int id) {
        return businessMapper.findBusinessById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addBusiness(Business business) {
        businessMapper.insertBusiness(business);
        return business.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateBusiness(Business business) {
        return businessMapper.updateBusiness(business);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBusiness(int id) {
        return businessMapper.deleteBusiness(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> getBusinessesPaged(int page, int size, String sortField) {
        log.debug("page: {}, size: {}, sortField: {}", page, size, sortField);
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page must be non-negative and size must be positive");
        }
        if (sortField == null || sortField.isEmpty()) {
            sortField = "id";
        }
        int offset = page * size;
        List<Business> businesses = businessMapper.findBusinessesPaged(offset, size, sortField);
        int total = businessMapper.countAllBusinesses();
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("businesses", businesses);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Business> searchBusinessesByName(String name) {
        log.debug("搜索招商加盟名称: {}", name);
        return businessMapper.searchBusinessesByName(name);
    }
}
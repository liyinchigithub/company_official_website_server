package com.cows.service;

import com.cows.entity.Brand;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 品牌信息服务接口
 * */
public interface BrandService {
    List<Brand> getAllBrands();
    Brand getBrandById(int id);
    int addBrand(Brand brand);
    int updateBrand(Brand brand);
    int deleteBrand(int id);
    List<Brand> getBrandsPaged(int page, int size, String sortField);
    Brand getBrandByName(String name); // 新增方法
}
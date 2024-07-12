package com.cows.serviceImpl;

import com.cows.entity.Brand;
import com.cows.mapper.BrandMapper;
import com.cows.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Brand> getAllBrands() {
        return brandMapper.findAllBrands();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Brand getBrandById(int id) {
        return brandMapper.findBrandById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addBrand(Brand brand) {
        brandMapper.insertBrand(brand);
        return brand.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateBrand(Brand brand) {
        return brandMapper.updateBrand(brand);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBrand(int id) {
        return brandMapper.deleteBrand(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Brand> getBrandsPaged(int page, int size, String sortField) {
        log.debug("page: {}, size: {}, sortField: {}", page, size, sortField);
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page must be non-negative and size must be positive");
        }
        if (sortField == null || sortField.isEmpty()) {
            sortField = "id";
        }
        int offset = page * size;
        return brandMapper.findBrandsPaged(offset, size, sortField);
    }
}
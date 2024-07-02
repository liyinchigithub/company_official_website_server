package com.cows.serviceImpl;

import com.cows.entity.ProductCategory;
import com.cows.mapper.ProductCategoryMapper;
import com.cows.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ProductCategory> getAllProductCategories() {
        return productCategoryMapper.findAllProductCategories();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductCategory getProductCategoryById(int id) {
        return productCategoryMapper.findProductCategoryById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addProductCategory(ProductCategory productCategory) {
        productCategoryMapper.insertProductCategory(productCategory);
        return productCategory.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateProductCategory(ProductCategory productCategory) {
        return productCategoryMapper.updateProductCategory(productCategory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteProductCategory(int id) {
        return productCategoryMapper.deleteProductCategory(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ProductCategory> getProductCategoriesPaged(int page, int size, String sortField) {
        log.debug("page: {}, size: {}, sortField: {}", page, size, sortField);
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page must be non-negative and size must be positive");
        }
        if (sortField == null || sortField.isEmpty()) {
            sortField = "id";
        }
        int offset = page * size;
        return productCategoryMapper.findProductCategoriesPaged(offset, size, sortField);
    }
}
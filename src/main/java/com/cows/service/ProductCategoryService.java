package com.cows.service;

import com.cows.entity.ProductCategory;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getAllProductCategories();
    ProductCategory getProductCategoryById(int id);
    int addProductCategory(ProductCategory productCategory);
    int updateProductCategory(ProductCategory productCategory);
    int deleteProductCategory(int id);
    List<ProductCategory> getProductCategoriesPaged(int page, int size, String sortField);
}
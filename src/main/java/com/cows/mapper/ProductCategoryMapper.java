package com.cows.mapper;

import com.cows.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ProductCategoryMapper {
    List<ProductCategory> findAllProductCategories();
    ProductCategory findProductCategoryById(int id);
    int insertProductCategory(ProductCategory productCategory);
    int updateProductCategory(ProductCategory productCategory);
    int deleteProductCategory(int id);
    List<ProductCategory> findProductCategoriesPaged(@Param("offset") int offset, @Param("limit") int limit, @Param("sortField") String sortField);
}
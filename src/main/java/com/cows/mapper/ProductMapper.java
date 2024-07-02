package com.cows.mapper;

import com.cows.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 商品信息
 * */
@Mapper
public interface ProductMapper {
    List<Product> findAllProducts();
    Product findProductById(int id);
    int insertProduct(Product product);
    int updateProduct(Product product);
    int deleteProduct(int id);
    List<Product> findProductsPaged(@Param("offset") int offset, @Param("limit") int limit, @Param("sortField") String sortField);
}
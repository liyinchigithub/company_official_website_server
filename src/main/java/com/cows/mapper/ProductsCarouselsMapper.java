package com.cows.mapper;

import com.cows.entity.ProductsCarousels;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ProductsCarouselsMapper {
    List<ProductsCarousels> findAllProductsCarousels();
    ProductsCarousels findProductsCarouselsById(int id);
    int insertProductsCarousels(ProductsCarousels productsCarousels);
    int updateProductsCarousels(ProductsCarousels productsCarousels);
    int deleteProductsCarousels(int id);
}
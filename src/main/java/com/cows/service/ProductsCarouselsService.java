package com.cows.service;

import com.cows.entity.ProductsCarousels;
import java.util.List;

public interface ProductsCarouselsService {
    List<ProductsCarousels> getAllProductsCarousels();
    ProductsCarousels getProductsCarouselsById(int id);
    int addProductsCarousels(ProductsCarousels productsCarousels);
    int updateProductsCarousels(ProductsCarousels productsCarousels);
    int deleteProductsCarousels(int id);
}
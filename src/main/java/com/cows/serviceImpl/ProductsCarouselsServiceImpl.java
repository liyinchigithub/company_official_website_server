package com.cows.serviceImpl;

import com.cows.entity.ProductsCarousels;
import com.cows.mapper.ProductsCarouselsMapper;
import com.cows.service.ProductsCarouselsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductsCarouselsServiceImpl implements ProductsCarouselsService {

    @Autowired
    private ProductsCarouselsMapper productsCarouselsMapper;

    @Override
    public List<ProductsCarousels> getAllProductsCarousels() {
        return productsCarouselsMapper.findAllProductsCarousels();
    }

    @Override
    public ProductsCarousels getProductsCarouselsById(int id) {
       ProductsCarousels productsCarousels = productsCarouselsMapper.findProductsCarouselsById(id);
       System.out.println("Retrieved from DB: " + productsCarousels);
       return productsCarousels;
   }

    @Override
    public int addProductsCarousels(ProductsCarousels productsCarousels) {
        return productsCarouselsMapper.insertProductsCarousels(productsCarousels);
    }

    @Override
    public int updateProductsCarousels(ProductsCarousels productsCarousels) {
        return productsCarouselsMapper.updateProductsCarousels(productsCarousels);
    }

    @Override
    public int deleteProductsCarousels(int id) {
        return productsCarouselsMapper.deleteProductsCarousels(id);
    }
}
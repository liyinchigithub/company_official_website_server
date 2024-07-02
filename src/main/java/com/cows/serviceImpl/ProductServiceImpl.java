package com.cows.serviceImpl;

import com.cows.entity.Product;
import com.cows.mapper.ProductMapper;
import com.cows.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

/**
 * 商品信息服务接口实现类
 * */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Product> getAllProducts() {
        return productMapper.findAllProducts();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Product getProductById(int id) {
        return productMapper.findProductById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addProduct(Product product) {
        productMapper.insertProduct(product);
        return product.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateProduct(Product product) {
        return productMapper.updateProduct(product);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteProduct(int id) {
        return productMapper.deleteProduct(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Product> getProductsPaged(int page, int size, String sortField) {
        log.debug("page: {}, size: {}, sortField: {}", page, size, sortField);
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page must be non-negative and size must be positive");
        }
        if (sortField == null || sortField.isEmpty()) {
            sortField = "id";
        }
        int offset = page * size;
        return productMapper.findProductsPaged(offset, size, sortField);
    }
}
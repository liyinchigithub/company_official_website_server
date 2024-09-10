package com.cows.serviceImpl;

import com.cows.entity.Product;
import com.cows.mapper.ProductMapper;
import com.cows.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品信息服务接口实现类
 */
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
        Product product = productMapper.findProductById(id);
        if (product != null && product.getDetailImages() == null) {
            product.setDetailImages(new String[0]); // 或者设置为默认值
        }
        return product;
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
    public Map<String, Object> getProductsPaged(int page, int size, String sortField) {
        log.debug("page: {}, size: {}, sortField: {}", page, size, sortField);
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page must be non-negative and size must be positive");
        }
        if (sortField == null || sortField.isEmpty()) {
            sortField = "id";
        }
        int offset = page * size;
        List<Product> products = productMapper.findProductsPaged(offset, size, sortField);
        int total = productMapper.countAllProducts();
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("products", products);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Product> searchProductsByName(String name) {
        log.debug("搜索商品名称: {}", name);
        return productMapper.searchProductsByName(name);
    }
}
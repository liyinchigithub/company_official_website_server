package com.cows.handler;

import com.cows.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class ProductArrayTypeHandler extends BaseTypeHandler<Product[]> {

    private static final Logger logger = LoggerFactory.getLogger(ProductArrayTypeHandler.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Product[] parameter, JdbcType jdbcType) throws SQLException {
        try {
            String json = objectMapper.writeValueAsString(parameter);
            ps.setString(i, json);
        } catch (JsonProcessingException e) {
            throw new SQLException("Error converting Product array to JSON", e);
        }
    }

    @Override
    public Product[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        logger.info("Getting JSON result from column {}: {}", columnName, json);
        return toProductArray(json);
    }

    @Override
    public Product[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        logger.info("Getting JSON result from column index {}: {}", columnIndex, json);
        return toProductArray(json);
    }

    @Override
    public Product[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        logger.info("Getting JSON result from column index {}: {}", columnIndex, json);
        return toProductArray(json);
    }

    private Product[] toProductArray(String json) throws SQLException {
        if (json == null || json.isEmpty()) {
            return new Product[0];
        }
        try {
            Product[] products = objectMapper.readValue(json, Product[].class);
            logger.info("Converted JSON to Product array: {}", (Object) products);
            return products;
        } catch (JsonProcessingException e) {
            throw new SQLException("Error converting JSON to Product array", e);
        }
    }
}
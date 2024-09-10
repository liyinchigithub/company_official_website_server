package com.cows.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;


import java.sql.*;

@Slf4j
@Component
public class IntArrayTypeHandler extends BaseTypeHandler<int[]> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, int[] parameter, JdbcType jdbcType) throws SQLException {
        try {
            String json = objectMapper.writeValueAsString(parameter);
            System.out.println("Setting JSON to DB: " + json); // 调试输出
            ps.setString(i, json);
        } catch (JsonProcessingException e) {
            throw new SQLException("Error converting int[] to JSON", e);
        }
    }

    @Override
    public int[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        System.out.println("Retrieved JSON from DB (columnName): " + json); // 调试输出
        return toIntArray(json);
    }

    @Override
    public int[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        System.out.println("Retrieved JSON from DB (columnIndex): " + json); // 调试输出
        return toIntArray(json);
    }

    @Override
    public int[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        System.out.println("Retrieved JSON from DB (CallableStatement): " + json); // 调试输出
        return toIntArray(json);
    }

    // 将json转换为int数组
    private int[] toIntArray(String json) throws SQLException {
        if (json == null || json.isEmpty()) {
            log.debug("JSON is null or empty, returning empty int[]");
            return new int[0]; // 返回空数组
        }
        try {
            int[] result = objectMapper.readValue(json, int[].class);
            log.debug("Converted JSON to int[]: {}", result);
            return result;
        } catch (JsonProcessingException e) {
            log.error("Error converting JSON to int[]", e);
            throw new SQLException("Error converting JSON to int[]", e);
        }
    }
}
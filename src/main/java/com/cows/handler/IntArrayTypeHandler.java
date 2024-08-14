package com.cows.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;

public class IntArrayTypeHandler extends BaseTypeHandler<int[]> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, int[] parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, objectMapper.writeValueAsString(parameter));
        } catch (JsonProcessingException e) {
            throw new SQLException("Error converting int[] to JSON", e);
        }
    }

    @Override
    public int[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toIntArray(rs.getString(columnName));
    }

    @Override
    public int[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toIntArray(rs.getString(columnIndex));
    }

    @Override
    public int[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toIntArray(cs.getString(columnIndex));
    }

    private int[] toIntArray(String json) throws SQLException {
        if (json == null || json.isEmpty()) {
            return new int[0];// 返回空数组
        }
        try {
           // 添加日志
           System.out.println("Parsing JSON: " + json);
           return objectMapper.readValue(json, int[].class);
        } catch (JsonProcessingException e) {
            throw new SQLException("Error converting JSON to int[]", e);
        }
    }
}
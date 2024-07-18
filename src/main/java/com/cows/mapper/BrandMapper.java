package com.cows.mapper;

import com.cows.entity.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 品牌信息
 * */
@Mapper
public interface BrandMapper {
    List<Brand> findAllBrands();
    Brand findBrandById(int id);
    int insertBrand(Brand brand);
    int updateBrand(Brand brand);
    int deleteBrand(int id);
    List<Brand> findBrandsPaged(@Param("offset") int offset, @Param("limit") int limit, @Param("sortField") String sortField);
    Brand findBrandByName(@Param("name") String name); 
    List<Brand> searchBrandsByName(@Param("name") String name);
}
package com.cows.mapper;

import com.cows.entity.Business;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 招商加盟信息
 * */
@Mapper
public interface BusinessMapper {
    List<Business> findAllBusinesses();
    Business findBusinessById(int id);
    int insertBusiness(Business business);
    int updateBusiness(Business business);
    int deleteBusiness(int id);
    List<Business> findBusinessesPaged(@Param("offset") int offset, @Param("limit") int limit, @Param("sortField") String sortField);
    int countAllBusinesses();
    List<Business> searchBusinessesByName(@Param("name") String name);
}
package com.cows.mapper;

import com.cows.entity.LbsInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LbsMapper {
    void insertLbsInfo(LbsInfo lbsInfo);
}
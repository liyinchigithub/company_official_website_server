package com.cows.mapper;

import com.cows.entity.BasicInformation;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BasicInformationMapper {
    List<BasicInformation> findAllBasicInformation(); // 查询所有基本信息
    BasicInformation findBasicInformationById(Long id); // 根据id查询基本信息
    int insertBasicInformation(BasicInformation basicInformation); // 插入基本信息
    int updateBasicInformation(BasicInformation basicInformation); // 更新基本信息
    int deleteBasicInformation(Long id); // 删除基本信息，逻辑删除，将isDeleted字段设置为1
    List<BasicInformation> getAllBasicInformation(int offset, int limit, String sortField); // 分页查询基本信息
}
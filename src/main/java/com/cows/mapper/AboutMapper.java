package com.cows.mapper;

import com.cows.entity.About;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 关于我们页面图片信息
 * */
@Mapper
public interface AboutMapper {
    List<About> findAllAbouts();
    About findAboutById(int id);
    int insertAbout(About about);
    int updateAbout(About about);
    int deleteAbout(int id);
    List<About> findAboutsPaged(@Param("offset") int offset, @Param("limit") int limit, @Param("sortField") String sortField);
    int countAllAbouts();
}
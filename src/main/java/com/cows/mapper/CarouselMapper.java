package com.cows.mapper;

import com.cows.entity.Carousel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface CarouselMapper {
    List<Carousel> findAllCarousels();
    Carousel findCarouselById(int id);
    int insertCarousel(Carousel carousel);
    int updateCarousel(Carousel carousel);
    int deleteCarousel(int id);
    List<Carousel> getAllCarousels(int offset, int limit, String sortField);
    @Select("SELECT COUNT(*) FROM Carousels WHERE imageUrl = #{imageUrl}")
    int countByImageUrl(String imageUrl);
}
package com.cows.service;

import com.cows.entity.Carousel;
import java.util.List;

public interface CarouselService {
    List<Carousel> getAllCarousels();
    Carousel getCarouselById(int id);
    int addCarousel(Carousel carousel);
    int updateCarousel(Carousel carousel);
    int deleteCarousel(int id);
    List<Carousel> getAllCarousels(int page, int size, String sortField);
}
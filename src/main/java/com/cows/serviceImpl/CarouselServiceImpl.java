package com.cows.serviceImpl;

import com.cows.entity.Carousel;
import com.cows.mapper.CarouselMapper;
import com.cows.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Carousel> getAllCarousels() {
        return carouselMapper.findAllCarousels();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Carousel getCarouselById(int id) {
        return carouselMapper.findCarouselById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addCarousel(Carousel carousel) {
        if (carouselMapper.countByImageUrl(carousel.getImageUrl()) > 0) {
            throw new IllegalArgumentException("图片URL已存在");
        }
        carouselMapper.insertCarousel(carousel);
        return carousel.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateCarousel(Carousel carousel) {
        return carouselMapper.updateCarousel(carousel);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteCarousel(int id) {
        return carouselMapper.deleteCarousel(id);
    }

    @Override
    public List<Carousel> getAllCarousels(int page, int size, String sortField) {
        log.debug("page" + page + " size" + size + " sortField" + sortField);
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page must be non-negative and size must be positive");
        }
        if (sortField == null || sortField.isEmpty()) {
            sortField = "id";
        }
        int offset = page * size;
        return carouselMapper.getAllCarousels(offset, size, sortField);
    }
}

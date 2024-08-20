package com.cows.serviceImpl;

import com.cows.entity.About;
import com.cows.mapper.AboutMapper;
import com.cows.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 关于我们页面图片服务接口实现类
 * */
@Slf4j
@Service
public class AboutServiceImpl implements AboutService {
    @Autowired
    private AboutMapper aboutMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<About> getAllAbouts() {
        return aboutMapper.findAllAbouts();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public About getAboutById(int id) {
        return aboutMapper.findAboutById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addAbout(About about) {
        aboutMapper.insertAbout(about);
        return about.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAbout(About about) {
        return aboutMapper.updateAbout(about);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAbout(int id) {
        return aboutMapper.deleteAbout(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> getAboutsPaged(int page, int size, String sortField) {
        log.debug("page: {}, size: {}, sortField: {}", page, size, sortField);
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page must be non-negative and size must be positive");
        }
        if (sortField == null || sortField.isEmpty()) {
            sortField = "id";
        }
        int offset = page * size;
        List<About> abouts = aboutMapper.findAboutsPaged(offset, size, sortField);
        int total = aboutMapper.countAllAbouts();
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("abouts", abouts);
        return result;
    }
}
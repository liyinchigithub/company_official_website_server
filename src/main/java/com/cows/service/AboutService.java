package com.cows.service;

import com.cows.entity.About;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

/**
 * 关于我们页面图片服务接口
 * */
public interface AboutService {
    List<About> getAllAbouts();
    About getAboutById(int id);
    int addAbout(About about);
    int updateAbout(About about);
    int deleteAbout(int id);
    Map<String, Object> getAboutsPaged(int page, int size, String sortField);
}
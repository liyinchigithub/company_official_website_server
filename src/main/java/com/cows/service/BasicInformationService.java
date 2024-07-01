package com.cows.service;

import com.cows.entity.BasicInformation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface BasicInformationService {
    List<BasicInformation> getAllBasicInformation();
    BasicInformation getBasicInformationById(Long id);
    int addBasicInformation(BasicInformation basicInformation);
    int updateBasicInformation(BasicInformation basicInformation);
    int deleteBasicInformation(Long id);
    List<BasicInformation> getAllBasicInformation(int page, int size, String sortField); // 分页查询基本信息
}
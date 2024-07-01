package com.cows.serviceImpl;

import com.cows.entity.BasicInformation;
import com.cows.mapper.BasicInformationMapper;
import com.cows.service.BasicInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@Service
public class BasicInformationServiceImpl implements BasicInformationService {
    @Autowired
    private BasicInformationMapper basicInformationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<BasicInformation> getAllBasicInformation() {
        return basicInformationMapper.findAllBasicInformation();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BasicInformation getBasicInformationById(Long id) {
        BasicInformation basicInformation = basicInformationMapper.findBasicInformationById(id);
        if (basicInformation == null) {
            throw new IllegalArgumentException("未找到ID为 " + id + " 的基本信息");
        }
            return basicInformation;
        }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addBasicInformation(BasicInformation basicInformation) {
        basicInformationMapper.insertBasicInformation(basicInformation);
        if (basicInformation.getId() == null) {
            throw new IllegalArgumentException("插入基本信息失败，ID未生成");
        }
        return Math.toIntExact(basicInformation.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateBasicInformation(BasicInformation basicInformation) {
        return basicInformationMapper.updateBasicInformation(basicInformation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBasicInformation(Long id) {
        return basicInformationMapper.deleteBasicInformation(id);
    }

    @Override
    public List<BasicInformation> getAllBasicInformation(int page, int size, String sortField) {
        log.debug("page: {}, size: {}, sortField: {}", page, size, sortField);
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page must be non-negative and size must be positive");
        }
        if (sortField == null || sortField.isEmpty()) {
            sortField = "id";
        }
        int offset = page * size;
        return basicInformationMapper.getAllBasicInformation(offset, size, sortField);
    }
}
package com.cows.serviceImpl;

import com.cows.entity.LbsInfo;
import com.cows.service.LbsService;
import com.cows.mapper.LbsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LbsServiceImpl implements LbsService {
    @Autowired
    private LbsMapper lbsMapper;

    @Override
    public void saveLocationInfo(LbsInfo lbsInfo) {
        lbsMapper.insertLbsInfo(lbsInfo);
    }
}
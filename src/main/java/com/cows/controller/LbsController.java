package com.cows.controller;

import com.cows.entity.LbsInfo;
import com.cows.service.LbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LbsController {
    @Autowired
    private LbsService lbsService;

    @PostMapping("/location")
    public void saveUserLocation(@RequestBody LbsInfo lbsInfo) {
        lbsService.saveLocationInfo(lbsInfo);
    }
}
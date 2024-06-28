package com.cows.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LbsInfo {

    private Long id;
    private Long userId;
    private Double latitude;
    private Double longitude;
    private String address;
    private Date createdAt;
    private Date updatedAt;
    // Getters and Setters
}
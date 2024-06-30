package com.cows.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String userName;
    private String password;
    @JsonProperty("isEnable")
    private boolean isEnable;
    @JsonProperty("isDeleted")
    private boolean isDeleted;
    // 自动生成getter和setter方法
}
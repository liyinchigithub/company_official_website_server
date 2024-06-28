package com.cows.commons.api;

import lombok.Data;
import org.springframework.stereotype.Component;


/**
 * CSDN教程
 * 统一返回json类
 * */
@Data
@Component
public class JsonResult<T> {
    private T data;// 返回数据
    private String code;//  状态码
    private String msg;//提示信息

    /**
     * 若没有数据返回，默认状态码为0，提示信息为：操作成功！
     */
    public JsonResult() {
        this.code = "0";
        this.msg = "操作成功！";
    }

    /**
     * 若没有数据返回，可以人为指定【状态码】和【提示信息】
     * @param code
     * @param msg
     */
    public JsonResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 有数据返回时，状态码为0，默认提示信息为：操作成功！
     * @param data
     */
    public JsonResult(T data) {
        this.data = data;
        this.code = "0";
        this.msg = "操作成功！";
    }

    /**
     * 有数据返回，状态码为0，人为指定【提示信息】
     * @param data
     * @param msg
     */
    public JsonResult(T data, String msg) {
        this.data = data;
        this.code = "0";
        this.msg = msg;
    }
    // 省略get和set方法
}
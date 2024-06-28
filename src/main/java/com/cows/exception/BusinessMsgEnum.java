package com.cows.exception;


import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * [业务异常]提示信息枚举类
 *
 * @Description: 由于在业务中，有很多异常，针对不同的业务，可能给出的提示信息不同，所以为了方便项目异常信息管理，我们一般会定义一个异常信息枚举类。
 */

public enum BusinessMsgEnum {
        /** 参数异常 */
        PARMETER_EXCEPTION("102", "参数异常!"),
        /** 等待超时 */
        SERVICE_TIME_OUT("103", "服务调用超时！"),
        /** 参数过大 */
        PARMETER_BIG_EXCEPTION("102", "输入的图片数量不能超过50张!"),
        /** 空指针 */
        NULL_POINTER("101", "空指针异常啦!!!"),
        /** 500 : 一劳永逸的提示也可以在这定义 */
        UNEXPECTED_EXCEPTION("500", "系统发生异常，请联系管理员！");


        /**
         * 消息码
         */
        private String code;
        /**
         * 消息内容
         */
        private String msg;

        BusinessMsgEnum(String code, String msg) {
                this.code = code;
                this.msg = msg;
        }
        public String getCode() {
                return code;
        }
        public String getMsg() {
                return msg;
        }
}
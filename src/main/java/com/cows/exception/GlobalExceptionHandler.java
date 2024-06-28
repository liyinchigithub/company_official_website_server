package com.cows.exception;

import com.cows.commons.api.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
@ResponseBody   // @ResponseBody注解是为了异常处理完之后给调用方输出一个 json 格式的封装数据。
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);// 打印日志

    /**
     * 缺少请求参数异常
     * @param ex HttpMessageNotReadableException
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public BaseResponse handleHttpMessageNotReadableException(MissingServletRequestParameterException ex) {
        log.error("缺少请求参数，{}", ex.getMessage());
        return new BaseResponse(400, "缺少必要的请求参数");
    }

    /**
     * @Description 处理空指针异常
     * @param ex NullPointerException
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleTypeMismatchException(NullPointerException ex) {
        log.error("空指针异常，{}", ex.getMessage());
        // 不使用枚举
//        return new BaseResponse(500, "空指针异常了");
        // 使用枚举
        return new BaseResponse(Integer.parseInt(BusinessMsgEnum.NULL_POINTER.getCode()),
                BusinessMsgEnum.NULL_POINTER.getMsg());// 业务枚举处理异常
    }



    /**
     * 处理数组越界异常
     * */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleIndexOutOfBoundsException(IndexOutOfBoundsException ex) {
        log.error("数组越界异常，{}", ex.getMessage());
        return new BaseResponse(500, "数组越界异常了");
    }


    /**
     * 系统异常（预期意外）
     * @param ex
     * 项目中，我们一般都会比较详细的去拦截一些常见异常，拦截 Exception 虽然可以一劳永逸，但是不利于我们去排查或者定位问题。
     * 实际项目中，可以把拦截 Exception 异常写在 GlobalExceptionHandler 最下面，如果都没有找到，最后再拦截一下 Exception 异常，保证输出信息友好
     *
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleUnexpectedServer(Exception ex) {
        log.error("系统异常：", ex);
        return new BaseResponse(500, "系统未知异常，请联系管理员");
    }

    /**
     * @Description: 处理 所有不可知的异常
     * @param ex
     * @return
     */
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
//        // 这里可以记录日志，发送通知等
//        return new ResponseEntity<>("请求参数错误", HttpStatus.BAD_REQUEST);
//    }
}

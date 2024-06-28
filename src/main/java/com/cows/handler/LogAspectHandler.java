package com.cows.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 * 定义一个切面，拦截com.itcodai.course09.controller包和子包下的所有方法
 * */
@Aspect
@Slf4j
@Component
public class LogAspectHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 定义一个切面，拦截com.itcodai.course09.controller包和子包下的所有方法
     */
    @Pointcut("execution(* com.example.lyc.springboot.demo..*.*(..))")
    public void pointCut() {
        // 方法体为空，表示该方法是一个切面
    }

    /**
     * 在上面定义的切面方法之前执行该方法
     * @param joinPoint jointPoint
     */
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("==============doBefore方法进入了==============");
        // 获取签名
        Signature signature = joinPoint.getSignature();
        // 获取切入的包名
        String declaringTypeName = signature.getDeclaringTypeName();
        // 获取即将执行的方法名
        String funcName = signature.getName();
        log.info("==============即将执行方法为: {}，属于{}包==============", funcName, declaringTypeName);

        // 也可以用来记录一些信息，比如获取请求的url和ip
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            // 获取请求url
            String url = request.getRequestURL().toString();
            // 获取请求ip
            String ip = request.getRemoteAddr();
            log.info("==================用户请求的url为：{}，ip地址为：{}==============", url, ip);
        } else {
            log.info("Not in a request context");
        }
    }

    /**
     * 在上面定义的切面方法之后执行该方法
     * @param joinPoint jointPoint
     */
    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("==============doAfter方法进入了==============");
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        log.info("==============方法{}已经执行完==============", method);
    }


    /**
     * 在上面定义的切面方法返回后执行该方法，可以捕获返回对象或者对返回对象进行增强
     * @param joinPoint joinPoint
     * @param result result
     */
    @AfterReturning(pointcut = "pointCut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {

        Signature signature = joinPoint.getSignature();
        String classMethod = signature.getName();
        if (result != null) {
            log.info("==============方法{}执行完毕，返回参数为：{}==============", classMethod, result);
            // 实际项目中可以根据业务做具体的返回值增强
            log.info("==============对返回参数进行业务上的增强：{}", result.toString() + "增强版==============");
        } else {
            log.info("==============方法{}执行完毕，但返回参数为 null==============", classMethod);
        }
    }


    /**
     * 在上面定义的切面方法执行抛异常时，执行该方法
     * @param joinPoint jointPoint
     * @param e e
     */
    @AfterThrowing(pointcut = "pointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        // 处理异常的逻辑
        log.info("==============执行方法{}出错，异常为：{}==============", method, e);
    }


    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void annotationCut() {

    }

}

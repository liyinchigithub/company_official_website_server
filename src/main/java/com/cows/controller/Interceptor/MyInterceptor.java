package com.cows.controller.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.lang.reflect.Method;

/**
 * 自定义拦截器
 * @author liyinchi
 * @date 2023/12/14
 */
public class MyInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

    /**
     * preHandle方法在请求处理之前进行调用。
     *
     * 该方法的执行时机是，当某个 url 已经匹配到对应的 Controller 中的某个方法，且在这个方法执行之前。
     * 所以 preHandle(……) 方法可以决定是否将请求放行，这是通过返回值来决定的，返回 true 则放行，返回 false 则不会向后执行。
     *
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 当前请求的处理器
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            String methodName = method.getName();
            logger.info("====拦截到了方法：{}，在该方法执行之前执行====", methodName);
        } else if (handler instanceof ResourceHttpRequestHandler) {
            logger.info("====拦截到了静态资源请求，跳过处理====");
        }

        return true;
    }

    /**
     * postHandle方法在请求处理之后，但是视图渲染之前被调用。
     *
     * 该方法的执行时机是，当某个 url 已经匹配到对应的 Controller 中的某个方法，且在执行完了该方法，但是在 DispatcherServlet 视图渲染之前。
     * 所以在这个方法中有个 ModelAndView 参数，可以在此做一些修改动作。
     *
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 当前请求的处理器
     * @param modelAndView 视图对象
     *
     * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("执行完方法之后进执行(Controller方法调用之后)，但是此时还没进行视图渲染");
    }

    /**
     * afterCompletion方法在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行。
     *
     * 该方法是在整个请求处理完成后（包括视图渲染）执行，这时做一些资源的清理工作，这个方法只有在 preHandle(……) 被成功执行后并且返回 true 才会被执行。
     *
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 当前请求的处理器
     * @param ex 异常对象
     * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("整个请求都处理完咯，DispatcherServlet也渲染了对应的视图咯，此时我可以做一些清理的工作了");
    }
}
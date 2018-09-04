package com.yanglf.usermanage.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class HttpAspect {


    Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.yanglf.usermanage.controller.*.*(..))")
    public void webLog(){}


    @Before("webLog()")
    public void log(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("########################请求信息###########################");
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        logger.info("########################请求结束###########################");
    }


    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturn(Object ret) throws Throwable{
        logger.info("方法返回值 ："+ret);
    }

}

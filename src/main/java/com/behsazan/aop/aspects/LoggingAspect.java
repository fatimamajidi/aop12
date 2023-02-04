package com.behsazan.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@Aspect
public class LoggingAspect {

    private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class.getName());

    private ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("[yyyy-mm-dd hh:mm:ss:SSS]");
        }
    };

    @Pointcut("@annotation(com.behsazan.aop.annotations.Loggable)")
    public void loggableMethods() {
    }

    @Before("loggableMethods()")
    public void logMethod(JoinPoint jp) {
        String className = jp.getTarget().getClass().getSimpleName();
        String methodName = jp.getSignature().getName();
        String args = Arrays.stream(jp.getArgs()).map(Object::toString).collect(Collectors.joining(","));
        String parameters = String.join(",", ((MethodSignature) jp.getSignature()).getParameterNames());
        logger.info("Executing : {}.{}({}) => {}", className, methodName, parameters, args);
    }

    @AfterReturning(pointcut = "loggableMethods()", returning = "result")
    public void logResult(JoinPoint jp, Object result) {
        String className = jp.getTarget().getClass().getSimpleName();
        String methodName = jp.getSignature().getName();
        String parameters = String.join(",", ((MethodSignature) jp.getSignature()).getParameterNames());
        logger.info("Complete : {}.{}({}) => {}", className, methodName, parameters, result);
    }

    @AfterThrowing(pointcut = "loggableMethods()", throwing = "exception")
    public void logException(JoinPoint jp, Exception exception) {
        String className = jp.getTarget().getClass().getSimpleName();
        String methodName = jp.getSignature().getName();
        logger.error(String.format("Complete : %s.%s", className, methodName), exception);
    }


}

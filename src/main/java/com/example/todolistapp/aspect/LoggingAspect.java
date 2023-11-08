package com.example.todolistapp.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.example.todolistapp.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Before executing: " + joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "execution(* com.example.todolistapp.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("After returning from: " + joinPoint.getSignature().toShortString());
    }

    @AfterThrowing(pointcut = "execution(* com.example.todolistapp.*.*(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("Exception in: " + joinPoint.getSignature().toShortString(), exception);
    }
}

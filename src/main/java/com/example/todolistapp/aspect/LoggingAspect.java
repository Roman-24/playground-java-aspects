package com.example.todolistapp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.todolistapp.service.impl.TaskServiceImpl.*(..))")
    public void logBeforeServiceMethods(JoinPoint joinPoint) {
        // Logging logic before executing any TodoService method
    }
}


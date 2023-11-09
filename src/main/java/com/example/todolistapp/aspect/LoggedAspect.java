package com.example.todolistapp.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Security aspect
 */
@Aspect
@Component
@Slf4j
public class LoggedAspect {

    private static final int MAX_LENGTH_OF_LOOGED_MESSAGE = 1000;

    @Pointcut("execution(public * *(..))")
    private static void publicMethod() {
        // not used - only aspect definition
    }

    @Pointcut("within(@com.example.todolistapp.annotation.AOSDLoggedInfo *)")
    private static void isAOSDLoggedInfo() {
        // not used - only aspect definition
    }

    @Pointcut("within(@com.example.todolistapp.annotation.AOSDTimeMonitor *)")
    private static void isAOSDTimeMonitor() {
        // not used - only aspect definition
    }

    private static void monitorTime(ProceedingJoinPoint joinPoint) throws Throwable {
        if (log.isInfoEnabled()) {
            long start = System.currentTimeMillis();
            joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - start;
            log.info("Duration of execution: {}ms", executionTime);
        }
    }

    private static Object logInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        if (log.isInfoEnabled()) {
            log.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), arraytoString(joinPoint.getArgs()));
            Object result = joinPoint.proceed();
            log.info("Exit: {}.{}() with result() = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    arraytoString(new Object[]{result}));
            return result;
        }
        return null;
    }

    private static String arraytoString(Object[] args) {
        StringBuilder sb = new StringBuilder();
        for (Object object : args) {
            sb.append(object);
            sb.append("; ");
        }
        String result = sb.toString();
        if ((result.length() > MAX_LENGTH_OF_LOOGED_MESSAGE)) {
            result = result.substring(0, MAX_LENGTH_OF_LOOGED_MESSAGE) + "...";
        }
        return result;
    }

    /**
     * Info log public method invocation
     *
     * @param joinPoint {@link ProceedingJoinPoint}
     * @return {@link Object}
     * @throws Throwable the throwable
     */
    @Around(value = "publicMethod() && isAOSDLoggedInfo()")
    public Object logInfoMethodInvocation(ProceedingJoinPoint joinPoint) throws Throwable {
        return logInfo(joinPoint);
    }

    /**
     * Monitor time public method invocation
     *
     * @param joinPoint {@link ProceedingJoinPoint}
     * @return {@link Object}
     * @throws Throwable the throwable
     */
    @Around(value = "publicMethod() && isAOSDTimeMonitor()")
    public void monitorTimeMethodInvocation(ProceedingJoinPoint joinPoint) throws Throwable {
        monitorTime(joinPoint);
    }

    @AfterThrowing(
            pointcut = "publicMethod() && isAOSDLoggedInfo()",
            throwing = "exception"
    )
    public void handleException(JoinPoint joinPoint, Exception exception) {
        log.error("Exception in method: {}, Exception message: {}, Payload: {} ",
                joinPoint.getSignature().toShortString(),
                exception.getMessage(),
                arraytoString(joinPoint.getArgs()));
    }

}

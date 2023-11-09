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
    private static void isAOSDLoggedLogInfo() {
        // not used - only aspect definition
    }

    /**
     * Info log public method invocation
     *
     * @param joinPoint {@link ProceedingJoinPoint}
     * @return {@link Object}
     * @throws Throwable the throwable
     */
    @Around(value = "publicMethod() && isAOSDLoggedLogInfo()")
    public Object logInfoMethodInvocation(ProceedingJoinPoint joinPoint) throws Throwable {
        return logInfo(joinPoint);
    }

    @AfterThrowing(
            pointcut = "publicMethod() && isAOSDLoggedLogInfo()",
            throwing = "exception"
    )
    public void handleException(JoinPoint joinPoint, Exception exception) {
        log.error("Exception in method: {}, Exception message: {}, Payload: {} ",
                joinPoint.getSignature().toShortString(),
                exception.getMessage(),
                arraytoString(joinPoint.getArgs()));
    }

    private static Object logInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        if (log.isInfoEnabled()) {
            log.info("Exit: {}.{}() with result({}ms) = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), executionTime,
                    arraytoString(new Object[]{result}));
        }
        return result;
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

}

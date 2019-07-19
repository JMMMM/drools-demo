package com.study.droolscore.aspect;

import com.study.droolscore.utils.LogUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * com.study.droolscore.aspect
 *
 * @author jimmy
 * @date 2019-07-11
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(public * com.study.droolscore.config.*.*(..))")
    public void pointCut() {
    }

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        long startTime = System.currentTimeMillis();
        Object object = proceedingJoinPoint.getTarget();
        String methodName = proceedingJoinPoint.getSignature().getName();
        LogUtils.printMemoryInfo("before",object.getClass().getName(), methodName, 0);
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        LogUtils.printMemoryInfo("after",object.getClass().getName(), methodName, System.currentTimeMillis() - startTime);
        return result;
    }
}

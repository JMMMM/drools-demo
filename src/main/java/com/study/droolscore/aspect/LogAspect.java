package com.study.droolscore.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * com.study.droolscore.aspect
 *
 * @author jimmy
 * @date 2019-07-11
 */
@Aspect
@Component
public class LogAspect {
    private static final long MB = 1048576L;

    @Pointcut("execution(public * com.study.droolscore.config.*.*(..))")
    public void pointCut() {
    }

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        long startTime = System.currentTimeMillis();
        Object object = proceedingJoinPoint.getTarget();
        String methodName = proceedingJoinPoint.getSignature().getName();
        printMemoryInfo("before",object.getClass().getName(), methodName, 0);
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        printMemoryInfo("after",object.getClass().getName(), methodName, System.currentTimeMillis() - startTime);
        return result;
    }

    private void printMemoryInfo(String header,String className, String method, long usedTime) {
        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memory.getHeapMemoryUsage();

        String info = String.format("\ninit: %s\t max: %s\t used: %s\t committed: %s\t use rate: %s\n",
                heapMemoryUsage.getInit() / MB + "MB",
                heapMemoryUsage.getMax() / MB + "MB", heapMemoryUsage.getUsed() / MB + "MB",
                heapMemoryUsage.getCommitted() / MB + "MB",
                heapMemoryUsage.getUsed() * 100 / heapMemoryUsage.getCommitted() + "%"

        );

        System.out.println(String.format("%s %s %s heap: %s 耗时：%d ms",header, className, method, info, usedTime));

        MemoryUsage nonHeapMemory = memory.getNonHeapMemoryUsage();

        info = String.format("init: %s\t max: %s\t used: %s\t committed: %s\t use rate: %s\n",
                nonHeapMemory.getInit() / MB + "MB",
                nonHeapMemory.getMax() / MB + "MB", nonHeapMemory.getUsed() / MB + "MB",
                nonHeapMemory.getCommitted() / MB + "MB",
                nonHeapMemory.getUsed() * 100 / nonHeapMemory.getCommitted() + "%"

        );

        System.out.println(String.format("%s : %s %s nonheap: %s 耗时：%d ms", header,className, method, info, usedTime));
    }
}

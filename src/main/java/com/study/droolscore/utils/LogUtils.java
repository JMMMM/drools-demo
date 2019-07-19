package com.study.droolscore.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * com.study.droolscore.utils
 *
 * @author jimmy
 * @date 2019-07-19
 */
public class LogUtils {

    private static final long MB = 1048576L;

    public static void printMemoryInfo(String header,String className, String method, long usedTime) {
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

package com.bootelk.web.logbackExt.utils;

/**
 * 实现日志追踪上下文
 */
public class LogTraceContext {

    /**
     * 初始化
     */
    public static void init(){
        ThreadUUIDContext.remove();
        IncrementIdContext.remove();
    }
}

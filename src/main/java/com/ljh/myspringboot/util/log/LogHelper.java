package com.ljh.myspringboot.util.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.filter.LevelFilter;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.util.OptionHelper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 特定的日志记录工具类
 * @Author: linjinhan
 * @CreateDate: 2018/10/31 14:18
 */
@Slf4j
public class LogHelper {

    //动态log容器
    private static final Map<String, Logger> container = new HashMap<>();

    /**
     * 获取某功能的特定logger
     * @param name
     * @return
     */
    //
    private static Logger getLogger(String name) {
        Logger logger = container.get(name);
        if (logger != null) {
            return logger;
        }
        synchronized (LogHelper.class) {
            logger = container.get(name);
            if (logger != null) {
                return logger;
            }
            logger = build(name);
            container.put(name, logger);
        }
        return logger;
    }

    /**
     * 生成某功能的特定logger
     * @param name
     * @return
     */
    private static Logger build(String name) {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        RollingFileAppender appender = new RollingFileAppender();
        appender.setName("FILE-" + name);
        appender.setAppend(true);
        appender.setContext(context);

        TimeBasedRollingPolicy rollingPolicy = new TimeBasedRollingPolicy();
        String fileNamePattern = OptionHelper.substVars("${LOG_HOME}/${PROJECT_NAME}/" + name + "/%d{yyyy-MM-dd_HH}.log", context);
        rollingPolicy.setFileNamePattern(fileNamePattern);
        rollingPolicy.setParent(appender);
        rollingPolicy.setContext(context);
        rollingPolicy.start();


        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setPattern("%msg%n");
        encoder.setCharset(StandardCharsets.UTF_8);
        encoder.setContext(context);
        encoder.start();

        LevelFilter filter = new LevelFilter();
        filter.setLevel(Level.INFO);
        filter.setOnMatch(FilterReply.ACCEPT);
        filter.setOnMismatch(FilterReply.DENY);

//        appender.setFile(OptionHelper.substVars("${LOG_HOME}/${PROJECT_NAME}/" + name + "/%d{yyyy-MM-dd_HH}.log",context));
//        appender.setPrudent(false);
        appender.setRollingPolicy(rollingPolicy);
        appender.setEncoder(encoder);
        appender.start();


        Logger logger = context.getLogger("FILE-" + name);
        logger.setAdditive(false);
        logger.setLevel(Level.INFO);
//        appender.setRollingPolicy(rollingPolicy);
        logger.addAppender(appender);
        return logger;
    }


    /**
     * 某功能的日志写入独立的文件目录，以JsonObject传入
     * @param name
     * @param logInfo
     */
    public static void log2File(String name, JsonObject logInfo) {
        getLogger(name).info(logInfo.toString());
    }

    /**
     * 某功能的日志写入独立的文件目录，以object和Class传入
     * @param name
     * @param clz
     * @param logInfo
     */
    public static void log2File(String name, Class clz,Object logInfo) {
        if(clz.isInstance(logInfo)){
            Gson gson = new Gson();
            getLogger(name).info(gson.toJson(clz.cast(logInfo)));
        }
    }

}

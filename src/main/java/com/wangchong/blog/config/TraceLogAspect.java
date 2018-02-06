package com.wangchong.blog.config;

import com.wangchong.blog.annotation.TraceLog;
import com.wangchong.blog.util.LoggerUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class TraceLogAspect {

    @Autowired
    private HttpServletRequest request;
    private static final String LOG_HEADER_TEMPLATE="%s:%s";
    private static Logger logger = LoggerFactory.getLogger(TraceLogAspect.class);



    @Around("@annotation(traceLog)")
    public Object traceLogFilter(ProceedingJoinPoint pjp, TraceLog traceLog ){
        String traceValue = traceLog.value();
        String traceName = traceLog.name();

        if (StringUtils.isBlank(traceValue)) {
            traceValue = "TraceLogAspect.traceLogFilter";
        }
        String logHeader = String.format(LOG_HEADER_TEMPLATE, traceValue, traceName);
        String reqParam = LoggerUtil.getRequestParametersLoggerInfo(request);
        logger.info("{} -Start {}", logHeader, reqParam);
        long beginTime=System.currentTimeMillis();
        Object executes = null;
        try {
            executes = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long endTime=System.currentTimeMillis();
        String processTime=LoggerUtil.getProcessTime(endTime-beginTime);
        String resParam = LoggerUtil.getResponseParametersLoggerInfo(executes);
        logger.info("{} -End {},{},{}", logHeader, reqParam, resParam,processTime);
        return executes;



    }
}

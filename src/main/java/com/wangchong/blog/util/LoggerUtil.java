package com.wangchong.blog.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class LoggerUtil {

    /** 入参 */
    private static final String REQUEST_PARAM_INFO = "【请求参数】:";
    /** 出参 */
    private static final String RESPONSE_PARAM_INFO = "【响应参数】:";
    /** 等于号 */
    private static final String EQUAL_STR = "=";
    /** AND */
    private static final String AND_STR = " & ";
    /** 花费时间 */
    private static final String PROCESS_TIME = "【耗时】:";


    public static String getRequestParametersLoggerInfo(
            HttpServletRequest request) {
        StringBuffer loggerInfoSb = new StringBuffer(REQUEST_PARAM_INFO);
        System.out.println(request.getRequestURI());

		Enumeration<String> e = request.getParameterNames();
		String parameterKey;
		while (e.hasMoreElements()) {
			parameterKey = e.nextElement();
			loggerInfoSb.append(parameterKey);
			loggerInfoSb.append(EQUAL_STR).append(
					request.getParameter(parameterKey)).append(AND_STR);
		}

        return loggerInfoSb.toString();
    }

    public static String getResponseParametersLoggerInfo(Object executes) {
        return LoggerUtil.RESPONSE_PARAM_INFO + JsonUtil.beanToJson(executes);
    }

    public static String getProcessTime(long processTime){
        return LoggerUtil.PROCESS_TIME + processTime + "ms";
    }



}

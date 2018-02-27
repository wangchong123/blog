package com.wangchong.blog.annotation;

import java.lang.annotation.*;

/**
 * 日志记录注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TraceLog {

    String value() default "";

    String name() default "";
}

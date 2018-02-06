package com.wangchong.blog.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TraceLog {

    String value() default "";

    String name() default "";
}

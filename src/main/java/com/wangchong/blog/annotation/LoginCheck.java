package com.wangchong.blog.annotation;

import java.lang.annotation.*;

/**
 * 登陆检查注解
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginCheck {
    String value() default "";
}

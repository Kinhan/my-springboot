package com.ljh.myspringboot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 需要登录才能进行操作的注解UserLoginToken
 * @Author: linjinhan
 * @CreateDate: 2019/1/7 15:54
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {
    boolean required() default true;
}

package com.haiwen.mybatis.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 变更日志
 *
 * @author sunsulei
 */
@Target({ElementType.TYPE})
@Retention(RUNTIME)
public @interface ChangeLog {
}

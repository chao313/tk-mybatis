package com.haiwen.core.framework.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局捕获异常
 */
@ControllerAdvice
public class ExceptionCatcher {

    @ExceptionHandler(value = NullPointerException.class)
    public String NullPointException() {
        return "空指针异常";
    }
}

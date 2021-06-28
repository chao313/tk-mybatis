package com.haiwen.mybatis.xx.statementhandler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

/**
 * 举例：拦截的是StatementHandler的prepare的方法
 * {@link StatementHandler#prepare(java.sql.Connection, java.lang.Integer)}
 */
@Component
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})}
)
@Slf4j
public class StatementHandlerPrepareInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();//拦截的对象
        Method method = invocation.getMethod();//拦截的方法
        Connection argsConnection = (Connection) invocation.getArgs()[0];//拦截的参数
        Integer argsInteger = (Integer) invocation.getArgs()[1];//拦截的参数
        Statement result = (Statement) invocation.proceed();
        log.info("query拦截对象:{}方法:{}参数:{},{},执行结果:{}", statementHandler.getClass().getSimpleName(), method, argsConnection, argsInteger, result);
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}

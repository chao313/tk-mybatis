package com.haiwen.mybatis.xx.statementhandler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

/**
 * 举例：拦截的是StatementHandler的 query 的方法
 * {@link StatementHandler#query(java.sql.Statement, org.apache.ibatis.session.ResultHandler)}
 */
@Component
@Intercepts({
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class})}
)
@Slf4j
public class StatementHandlerQueryInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();//拦截的对象
        Method method = invocation.getMethod();//拦截的方法
        Statement argsStatement = (Statement) invocation.getArgs()[0];//拦截的参数
        ResultHandler argsResultHandler = (ResultHandler) invocation.getArgs()[1];//拦截的参数
        List<String> result = (List) invocation.proceed();
        log.info("query拦截对象:{}方法:{}参数:{},{},执行结果:{}", statementHandler.getClass().getSimpleName(), method, argsStatement, argsResultHandler, result);
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

//package com.haiwen.mybatis.plugin;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.executor.statement.StatementHandler;
//import org.apache.ibatis.plugin.*;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.ibatis.reflection.SystemMetaObject;
//import org.springframework.stereotype.Component;
//
//import java.sql.Connection;
//import java.util.Properties;
//
///**
// * @author chao
// * @version 1.0
// * @description: mybatis拦截StatementHandler
// * @date 2021/6/23
// */
//@Slf4j
//@Component
//@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
//public class MyBatisInterceptorStatementHandler implements Interceptor {
//    private static String SQL_PATH = "delegate.boundSql.sql";
//
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
//        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
//        Object value = metaObject.getValue(SQL_PATH);
//        log.info("原有的sql为{}", value);
//        metaObject.setValue(SQL_PATH, value.toString()      + " limit 2");
//        log.info("设置之后的sql为{}", metaObject.getValue("delegate.boundSql.sql"));
//        return invocation.proceed();
//    }
//
//    @Override
//    public Object plugin(Object target) {
//        return Plugin.wrap(target, this);
//    }
//
//    @Override
//    public void setProperties(Properties properties) {
//    }
//}
//
//

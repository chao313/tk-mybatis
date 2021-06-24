//package com.haiwen.mybatis.plugin;
//
//import cn.hutool.core.annotation.AnnotationUtil;
//import cn.hutool.core.collection.CollUtil;
//import cn.hutool.core.convert.Convert;
//import cn.hutool.core.util.*;
//import cn.hutool.extra.spring.SpringUtil;
//import cn.hutool.json.JSONUtil;
//import com.enn.biz.common.exception.BaseException;
//import com.enn.biz.core.service.impl.OperateLogService;
//import com.enn.biz.dal.annotation.ChangeLogField;
//import com.enn.biz.dal.common.BaseDbPo;
//import com.enn.biz.dal.po.mbg.OperateLog;
//import com.enn.biz.dal.po.mbg.OperateLog.FieldValue;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.binding.MapperMethod.ParamMap;
//import org.apache.ibatis.executor.Executor;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.mapping.SqlCommandType;
//import org.apache.ibatis.plugin.*;
//import org.apache.ibatis.session.defaults.DefaultSqlSession.StrictMap;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Component;
//import tk.mybatis.mapper.MapperException;
//import tk.mybatis.mapper.common.Mapper;
//import tk.mybatis.mapper.entity.EntityTable;
//import tk.mybatis.mapper.entity.Example;
//import tk.mybatis.mapper.mapperhelper.EntityHelper;
//import tk.mybatis.mapper.util.MsUtil;
//
//import javax.annotation.Resource;
//import java.lang.reflect.Field;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Properties;
//import java.util.stream.Collectors;
//
///**
// * 拦截部分数据变动,进行比较并入库.
// *
// * @author sunsulei
// */
//@Component
//@Intercepts({
//        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
//})
//@Slf4j
//public class MysqlChangeLogInterceptor2 implements Interceptor {
//
//    private static final MapperException INTERCEPTOR_EXCEPTION = new MapperException("拦截到错误情况,请检查数据或联系产品中心");
//
//    @Resource
//    @Lazy
//    private OperateLogService operateLogService;
//
//
//    private Object addLog(MappedStatement mappedStatement, Object param, Invocation invocation) throws Exception {
//
//        switch (mappedStatement.getSqlCommandType()) {
//            case INSERT:
//                if (param instanceof StrictMap) {
//                    //当前为批量新增.
//                    Object tempList = ((StrictMap<?>) param).get("list");
//                    if (ObjectUtil.isEmpty(tempList)) {
//                        throw INTERCEPTOR_EXCEPTION;
//                    }
//
//                    Object proceed = invocation.proceed();
//
//                    List<?> list = Convert.toList(tempList);
//                    Object tempNewValue = list.get(0);
//                    if (needLog(tempNewValue)) {
//                        EntityTable entityTable = EntityHelper.getEntityTable(tempNewValue.getClass());
//
//                        List<Long> ids = CollUtil.getFieldValues(list, "id", Long.class);
//
//                        operateLogService.newLogList(SqlCommandType.INSERT, entityTable.getName(), ids);
//                    }
//
//                    return proceed;
//                } else if (needLog(param)) {
//                    Object proceed = invocation.proceed();
//                    EntityTable entityTable = EntityHelper.getEntityTable(param.getClass());
//                    operateLogService.newLog(SqlCommandType.INSERT, entityTable.getName(), ((BaseDbPo) param).getId());
//                    return proceed;
//                }
//
//                break;
//            case UPDATE:
//
//                //根据 id 删除.
//                if (param instanceof Long) {
//                    Class<?> mapperClass = MsUtil.getMapperClass(mappedStatement.getId());
//                    Mapper<Object> mapper = (Mapper<Object>) SpringUtil.getBean(mapperClass);
//                    Object oldValue = mapper.selectByPrimaryKey(param);
//                    if (needLog(oldValue)) {
//                        EntityTable entityTable = EntityHelper.getEntityTable(oldValue.getClass());
//                        operateLogService.newLog(SqlCommandType.DELETE, entityTable.getName(), (Long) param);
//                    }
//                } else if (needLog(param)) {
//                    EntityTable entityTable = EntityHelper.getEntityTable(param.getClass());
//                    Mapper<Object> objectMapper = dataMapper(entityTable);
//                    Object oldValue = objectMapper.selectByPrimaryKey(((BaseDbPo) param).getId());
//                    if (needLog(oldValue)) {
//                        //删除字段的变更,直接记为删除. [旧值未删除,新值已删除]的情况下.
//                        if (BooleanUtil.isTrue(((BaseDbPo) param).getDel()) && BooleanUtil.isFalse(((BaseDbPo) oldValue).getDel())) {
//                            operateLogService.newLog(SqlCommandType.DELETE, entityTable.getName(), ((BaseDbPo) oldValue).getId());
//                        } else {
//                            List<FieldValue> fieldValues = diffField(oldValue, param);
//
//                            if (CollUtil.isNotEmpty(fieldValues)) {
//                                operateLogService.newLog(SqlCommandType.UPDATE, entityTable.getName(), ((BaseDbPo) param).getId(), JSONUtil.toJsonStr(fieldValues));
//                            }
//                        }
//                    }
//                } else if (param instanceof Example && needLog(((Example) param).getEntityClass())) {
//                    EntityTable entityTable = EntityHelper.getEntityTable(((Example) param).getEntityClass());
//                    Mapper<Object> objectMapper = dataMapper(entityTable);
//                    List<Object> list = objectMapper.selectByExample(param);
//
//                    if (CollUtil.isNotEmpty(list)) {
//                        List<Long> ids = CollUtil.getFieldValues(list, "id", Long.class);
//                        operateLogService.newLogList(SqlCommandType.DELETE, entityTable.getName(), ids);
//
//                    }
//                } else if (param instanceof ParamMap) {
//                    Object record = ((ParamMap<?>) param).get("record");
//                    Object example = ((ParamMap<?>) param).get("example");
//
//                    if (needLog(record)) {
//                        EntityTable entityTable = EntityHelper.getEntityTable(record.getClass());
//                        Mapper<Object> objectMapper = dataMapper(entityTable);
//
//                        List<Object> objects = objectMapper.selectByExample(example);
//
//                        objects.forEach(oldValue -> {
//                            if (oldValue instanceof BaseDbPo && record instanceof BaseDbPo) {
//                                //删除字段的变更,直接记为删除. [旧值未删除,新值已删除]的情况下.
//                                if (BooleanUtil.isTrue(((BaseDbPo) record).getDel()) && BooleanUtil.isFalse(((BaseDbPo) oldValue).getDel())) {
//                                    operateLogService.newLog(SqlCommandType.DELETE, entityTable.getName(), ((BaseDbPo) oldValue).getId());
//                                } else {
//                                    List<FieldValue> fieldValues = diffField(oldValue, record);
//                                    if (CollUtil.isNotEmpty(fieldValues)) {
//                                        operateLogService.newLog(SqlCommandType.UPDATE, entityTable.getName(), ((BaseDbPo) oldValue).getId(), JSONUtil.toJsonStr(fieldValues));
//                                    }
//                                }
//                            }
//                        });
//                    }
//                }
//                break;
//            default:
//                return invocation.proceed();
//        }
//        return invocation.proceed();
//    }
//
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//
//        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
//        SqlCommandType sqlCommandType = (mappedStatement).getSqlCommandType();
//
//        if (sqlCommandType.equals(SqlCommandType.DELETE)) {
//            throw BaseException.of("🚫禁止删除操作!🚫");
//        }
//
//        Object newObj = ArrayUtil.get(invocation.getArgs(), 1);
//        return addLog(mappedStatement, newObj, invocation);
//    }
//
//    /**
//     * 判断当前 obj 是否满足记 log ,必须继承 BaseDbPo且增加 ChangeLog 注解.
//     */
//    private boolean needLog(Object obj) {
//        if (obj instanceof Class) {
//            try {
//                obj = ((Class<?>) obj).newInstance();
//            } catch (Exception ignore) {
//
//            }
//        }
//        return obj instanceof BaseDbPo && getChangeLog(obj.getClass()) != null;
//    }
//
//    private ChangeLog getChangeLog(Class<?> clazz) {
//        return AnnotationUtil.getAnnotation(clazz, ChangeLog.class);
//    }
//
//    private <T> Mapper<T> dataMapper(EntityTable entityTable) {
//        Object bean = SpringUtil.getBean(StrUtil.lowerFirst(entityTable.getEntityClass().getSimpleName()) + "Mapper");
//        if (!(bean instanceof Mapper)) {
//            throw INTERCEPTOR_EXCEPTION;
//        }
//        return SpringUtil.getBean(StrUtil.lowerFirst(entityTable.getEntityClass().getSimpleName()) + "Mapper");
//    }
//
//    /**
//     * 新旧两个对象根据 `ChangeLogField` 注解标识的字段进行比较变更项
//     */
//    private List<OperateLog.FieldValue> diffField(Object oldObj, Object newObj) {
//
//        List<Field> fieldList = Arrays.stream(ReflectUtil.getFields(oldObj.getClass()))
//                .filter(field -> AnnotationUtil.getAnnotation(field, ChangeLogField.class) != null)
//                .collect(Collectors.toList());
//
//        return fieldList.stream()
//                .map(field -> {
//                    Object oldValue = ReflectUtil.getFieldValue(oldObj, field);
//                    Object newValue = ReflectUtil.getFieldValue(newObj, field);
//                    String desc = field.getAnnotation(ChangeLogField.class).value();
//                    return OperateLog.FieldValue.of(field.getName(), desc, oldValue, newValue);
//                })
//                //更新时允许不更新null值 所以过滤掉新值为 null 的情况.
//                .filter(e -> e.getNewValue() != null)
//                .filter(e -> ObjectUtil.notEqual(e.getNewValue(), e.getOldValue()))
//                .collect(Collectors.toList());
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

package com.lind.basic.mybatis;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间拦截器.
 */
@Intercepts({@Signature(
    type = Executor.class,
    method = "update",
    args = {MappedStatement.class, Object.class, Integer.class})})
public class TimeFullInterceptor implements Interceptor {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public Object intercept(Invocation invocation) throws Throwable {

    MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

    // 获取 SQL 命令
    SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

    // 获取参数
    Object parameter = invocation.getArgs()[1];

    // 获取私有成员变量
    Field[] declaredFields = parameter.getClass().getDeclaredFields();

    for (Field field : declaredFields) {
      if (field.getAnnotation(CreatedOnFuncation.class) != null) {
        if (SqlCommandType.INSERT.equals(sqlCommandType)) { // insert 语句插入 createTime
          field.setAccessible(true);
          field.set(parameter, new Date());
        }
      }

      if (field.getAnnotation(UpdatedOnFuncation.class) != null) { // insert 或 update 语句插入 updateTime
        if (SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)) {
          field.setAccessible(true);
          field.set(parameter, new Date());
        }
      }
    }

    return invocation.proceed();
  }

  @Override
  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  @Override
  public void setProperties(Properties properties) {
  }
}
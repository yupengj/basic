package com.lind.basic.entity;

import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Properties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

/**
 * 时间拦截器.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Intercepts( {
    @Signature(
        type = org.apache.ibatis.executor.Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class})})
public class CreateUpdateTimeInterceptor extends AbstractSqlParserHandler implements Interceptor {

  private static final Log logger = LogFactory.getLog(com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor.class);

  private Properties properties;

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
          field.set(parameter, LocalDateTime.now());
        }
      }

      if (field.getAnnotation(UpdatedOnFuncation.class) != null) { // insert 或 update 语句插入 updateTime
        if (SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)) {
          field.setAccessible(true);
          field.set(parameter, LocalDateTime.now());
        }
      }
    }

    return invocation.proceed();
  }

  @Override
  public Object plugin(Object target) {
    if (target instanceof org.apache.ibatis.executor.Executor) {
      return Plugin.wrap(target, this);
    }
    return target;
  }

  @Override
  public void setProperties(Properties prop) {
    this.properties = prop;
  }
}

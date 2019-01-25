package com.lind.basic.entity.mybatis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UpdatedOnFuncation {
  /**
   * default .
   * @return
   */
  String value() default "";
}
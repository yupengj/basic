package com.lind.basic.mybatis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface CreatedOnFuncation {
  /**
   * default .
   * @return
   */
  String value() default "";
}
package com.lind.basic.util;

import com.lind.basic.enums.BaseEnum;
import java.util.Objects;
import java.util.stream.Stream;

public class EnumUtils {
  /**
   * 枚举工具类.
   */
  public static <T extends Enum<?> & BaseEnum> T codeOf(Class<T> enumClass, Integer code) {
    T[] enumConstants = enumClass.getEnumConstants();
    return Stream.of(enumConstants)
        .filter(e -> Objects.equals(e.getCode(), code))
        .findFirst()
        .orElse(null);
  }
}

package com.lind.basic.util;

import com.lind.basic.BaseTest;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

public class CompareTest extends BaseTest {
  @Test
  public void latestMonthYearTest() {
    List<YearMonth> list = Arrays.asList(
        YearMonth.of(2018, 1),
        YearMonth.of(2018, 3),
        YearMonth.of(2018, 2));
    list.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
    YearMonth latest = list.stream().sorted(Comparator.reverseOrder()).findFirst().orElse(null);
    Assert.assertEquals(YearMonth.of(2018, 3), latest);
  }
}

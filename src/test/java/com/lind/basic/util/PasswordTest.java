package com.lind.basic.util;

import com.lind.basic.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class PasswordTest extends BaseTest {
  private static final String SITE_WIDE_SECRET = "my-secret-key";
  private static final PasswordEncoder passwordEncoder = new StandardPasswordEncoder(
      SITE_WIDE_SECRET);

  @Test
  public void passwordEncoderTest() {
    String old = passwordEncoder.encode("abc1234");
    Assert.assertTrue(passwordEncoder.matches("abc1234",old));
  }
}

package com.sainsburys;

import java.math.BigDecimal;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
  public static Test suite() {
    return new TestSuite(AppTest.class);
  }

  public static void testScanningALavendarHeartCalculatesTotal() {
    App app = new App();
    app.scan("heart");
    BigDecimal expectedTotal = new BigDecimal("9.25");
    assertEquals(expectedTotal, app.total());
  }
}

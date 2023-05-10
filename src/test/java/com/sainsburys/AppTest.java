package com.sainsburys;

import java.math.BigDecimal;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
  public static Test suite() {
    return new TestSuite(AppTest.class);
  }

  public void testScanningALavendarHeartCalculatesTotal() {
    App app = new App();
    app.scan("heart");
    BigDecimal expectedTotal = new BigDecimal("9.25");
    assertEquals(expectedTotal, app.total());
  }

  public void testScanningHeartAndCufflinksAddsPricesTogether() {
    App app = new App();
    app.scan("heart");
    app.scan("cufflinks");
    BigDecimal expectedTotal = new BigDecimal("54.25");
    assertEquals(expectedTotal, app.total());
  }

  public static void testAllowsForCallingMultipleProductsInScan() {
  App app = new App();
  app.scan("heart,cufflinks");
  BigDecimal expectedTotal = new BigDecimal("54.25");
  assertEquals(expectedTotal, app.total());
  }
}

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

  public static void testReducesTwoHeartsTo850EachToSellMore() {
    App app = new App();
    app.scan("heart,heart");
    BigDecimal expectedTotal = new BigDecimal("17.00");
    assertEquals(expectedTotal, app.total());
  }
  
  public static void testReducesThreeHeartsTo850EachToSellMore() {
    App app = new App();
    app.scan("heart,heart,heart");
    BigDecimal expectedTotal = new BigDecimal("25.50");
    assertEquals(expectedTotal, app.total());
  }
}

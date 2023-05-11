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
    assertEquals(new BigDecimal("9.25"), app.total());
  }

  public void testScanningHeartAndCufflinksAddsPricesTogether() {
    App app = new App();
    app.scan("heart");
    app.scan("cufflinks");
    assertEquals(new BigDecimal("54.25"), app.total());
  }

  public static void testAllowsForCallingMultipleProductsInScan() {
    App app = new App();
    app.scan("heart,cufflinks");
    assertEquals(new BigDecimal("54.25"), app.total());
  }

  public static void testReducesTwoHeartsTo850EachToSellMore() {
    App app = new App();
    app.scan("heart,heart");
    assertEquals(new BigDecimal("17.00"), app.total());
  }
  
  public static void testReducesThreeHeartsTo850EachToSellMore() {
    App app = new App();
    app.scan("heart,heart,heart");
    assertEquals(new BigDecimal("25.50"), app.total());
  }

  public static void testTotalsUpHeartsCufflinksAndTshirts() {
    App app = new App();
    app.scan("heart,cufflinks,tshirt");
    assertEquals(new BigDecimal("74.20"), app.total());
  }
}

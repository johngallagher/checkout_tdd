package com.sainsburys;

import java.math.BigDecimal;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
  public static Test suite() {
    return new TestSuite(AppTest.class);
  }

  public void testScanningOneHeartTotalsHeartUnitPrice() {
    App app = new App();
    app.scan("heart");
    assertEquals(new BigDecimal("9.25"), app.total());
  }

  public void testScanningOneTshirtTotalsTshirtUnitPrice() {
    App app = new App();
    app.scan("tshirt");
    assertEquals(new BigDecimal("19.95"), app.total());
  }

  public void testScanningOneCufflinksTotalsCufflinksUnitPrice() {
    App app = new App();
    app.scan("cufflinks");
    assertEquals(new BigDecimal("45.00"), app.total());
  }

  public void testScanningHeartAndCufflinksAddsUnitPricesTogether() {
    App app = new App();
    app.scan("heart,cufflinks");
    assertEquals(new BigDecimal("54.25"), app.total());
  }

  public void testScanningCufflinksAndTshirtAddsUnitPricesTogether() {
    App app = new App();
    app.scan("heart,tshirt");
    assertEquals(new BigDecimal("29.20"), app.total());
  }

  public void testDiscountsTwoOrMoreHeartsToSellMore() {
    App app = new App();
    app.scan("heart,heart");
    assertEquals(new BigDecimal("17.00"), app.total());
  }
}

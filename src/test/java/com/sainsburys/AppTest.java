package com.sainsburys;

import java.math.BigDecimal;
import java.math.RoundingMode;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
  private static final BigDecimal DISCOUNTED_HEART_UNIT_PRICE = new BigDecimal("8.50");
  private static final BigDecimal HEART_UNIT_PRICE = new BigDecimal("9.25");
  private static final BigDecimal CUFFLINKS_UNIT_PRICE = new BigDecimal("45.00");
  private static final BigDecimal TSHIRT_UNIT_PRICE = new BigDecimal("19.95");

  public static Test suite() {
    return new TestSuite(AppTest.class);
  }

  public void testScanningOneHeartTotalsHeartUnitPrice() {
    App app = new App();
    app.scan("heart");
    assertEquals(HEART_UNIT_PRICE, app.total());
  }

  public void testScanningOneTshirtTotalsTshirtUnitPrice() {
    App app = new App();
    app.scan("tshirt");
    assertEquals(TSHIRT_UNIT_PRICE, app.total());
  }

  public void testScanningOneCufflinksTotalsCufflinksUnitPrice() {
    App app = new App();
    app.scan("cufflinks");
    assertEquals(CUFFLINKS_UNIT_PRICE, app.total());
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

  public void testDiscountsLargeOrders() {
    App app = new App();
    app.scan("heart,cufflinks,tshirt");
    BigDecimal totalBeforeDiscount = HEART_UNIT_PRICE
      .add(CUFFLINKS_UNIT_PRICE)
      .add(TSHIRT_UNIT_PRICE);
    BigDecimal largeOrderDiscount = totalBeforeDiscount.multiply(new BigDecimal("0.10")).setScale(2, RoundingMode.HALF_UP);
    assertEquals(totalBeforeDiscount.subtract(largeOrderDiscount), app.total());
  }
  
  public void testDiscountsVeryLargeOrders() {
    App app = new App();
    app.scan("heart,cufflinks,cufflinks,tshirt,tshirt");
    BigDecimal totalBeforeDiscount = HEART_UNIT_PRICE
      .add(CUFFLINKS_UNIT_PRICE)
      .add(CUFFLINKS_UNIT_PRICE)
      .add(TSHIRT_UNIT_PRICE)
      .add(TSHIRT_UNIT_PRICE);
    BigDecimal largeOrderDiscount = totalBeforeDiscount.multiply(new BigDecimal("0.10")).setScale(2, RoundingMode.HALF_UP);
    assertEquals(totalBeforeDiscount.subtract(largeOrderDiscount), app.total());
  }
  
  public void testDiscountsVeryLargeOrdersAndAppliesMultibuyHeartDiscount() {
    App app = new App();
    app.scan("heart,heart,cufflinks,cufflinks,tshirt,tshirt");
    BigDecimal totalBeforeDiscount = DISCOUNTED_HEART_UNIT_PRICE
      .add(DISCOUNTED_HEART_UNIT_PRICE)
      .add(CUFFLINKS_UNIT_PRICE)
      .add(CUFFLINKS_UNIT_PRICE)
      .add(TSHIRT_UNIT_PRICE)
      .add(TSHIRT_UNIT_PRICE);
    BigDecimal totalDiscount = totalBeforeDiscount.multiply(new BigDecimal("0.10")).setScale(2, RoundingMode.HALF_UP);
    assertEquals(totalBeforeDiscount.subtract(totalDiscount), app.total());
  }
}

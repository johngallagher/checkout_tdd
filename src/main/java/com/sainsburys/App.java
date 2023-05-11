package com.sainsburys;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class App {

  private static final BigDecimal HEART_MULTIBUY_DISCOUNT_PER_ITEM = new BigDecimal("0.75");
  private static final BigDecimal DISCOUNT_MULTIPLIER = new BigDecimal("0.1");
  private static final BigDecimal LARGE_ORDER_THRESHOLD = new BigDecimal("60");
  private final ArrayList<String> products;

  public App() {
    this.products = new ArrayList<String>();
  }

  public static void main(String[] products) {
    App app = new App();
    for (String product : products) {
      app.scan(product);
    }
    System.out.println("Total is:");
    System.out.println("£" + app.total());
  }

  public void scan(String rawProducts) {
    String[] products = rawProducts.split(",");
    for (String product : products) {
      this.products.add(product);
    }
  }

  private BigDecimal calculateHeartMultibuyDiscount() {
    long numberOfHearts = this.products.stream().filter(n -> n.equals("heart")).count();
    if (numberOfHearts > 1) {
      return HEART_MULTIBUY_DISCOUNT_PER_ITEM.multiply(toBigDecimal(numberOfHearts));
    } else {
      return new BigDecimal("0");
    }
  }

  public BigDecimal total() {
    BigDecimal runningTotal = new BigDecimal("0");
    for (String product : this.products) {
      runningTotal = runningTotal.add(priceForProduct(product));
    }
    runningTotal = runningTotal.subtract(calculateHeartMultibuyDiscount());
    runningTotal = runningTotal.subtract(calculateLargeOrderDiscount(runningTotal));
    return runningTotal;
  }

  private BigDecimal calculateLargeOrderDiscount(BigDecimal runningTotal) {
    BigDecimal largeOrderDiscount = new BigDecimal("0");
    if (runningTotal.compareTo(LARGE_ORDER_THRESHOLD) > 0) {
      largeOrderDiscount = rounded(runningTotal.multiply(DISCOUNT_MULTIPLIER));
    }
    return largeOrderDiscount;
  }

  private BigDecimal priceForProduct(String product) {
    if (product.equals("heart")) {
      return new BigDecimal("9.25");
    } else if (product.equals("cufflinks")) {
      return new BigDecimal("45.00");
    } else if (product.equals("tshirt")) {
      return new BigDecimal("19.95");
    } else {
      throw new RuntimeException("Product " + product + " does not have a price.");
    }
  }

  private BigDecimal rounded(BigDecimal number) {
    return number.setScale(2, RoundingMode.HALF_UP);
  }

  private BigDecimal toBigDecimal(long numberOfHearts) {
    return new BigDecimal(String.valueOf(numberOfHearts));
  }

}

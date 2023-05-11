package com.sainsburys;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class App {

  private final ArrayList<String> products;

  public App() {
    this.products = new ArrayList<String>();
  }

  public static void main(String[] products) {
  }

  public void scan(String rawProducts) {
    String[] products = rawProducts.split(",");
    for (String product : products) {
      this.products.add(product);
    }
  }

  private BigDecimal calculateDiscount() {
    long numberOfHearts = this.products.stream().filter(n -> n.equals("heart")).count();
    if (numberOfHearts > 1) {
      BigDecimal discountPerHeart = new BigDecimal("0.75");
      return discountPerHeart.multiply(new BigDecimal(numberOfHearts));
    } else {
      return new BigDecimal("0");
    }
  }

  public BigDecimal total() {
    BigDecimal runningTotal = new BigDecimal("0");
    for (String product : this.products) {
      runningTotal = runningTotal.add(priceForProduct(product));
    }
    runningTotal = runningTotal.subtract(calculateDiscount());
    runningTotal = runningTotal.subtract(calculateLargeOrderDiscount(runningTotal));
    return runningTotal;
  }

  private BigDecimal calculateLargeOrderDiscount(BigDecimal runningTotal) {
    BigDecimal largeOrderDiscount = new BigDecimal("0");
    if (runningTotal.compareTo(new BigDecimal("60")) > 0) {
      largeOrderDiscount = runningTotal.multiply(new BigDecimal("0.1")).setScale(2, RoundingMode.HALF_UP);
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
}

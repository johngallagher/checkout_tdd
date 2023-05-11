package com.sainsburys;

import java.math.BigDecimal;
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
      runningTotal = runningTotal.add(this.priceForProduct(product));
    }
    runningTotal = runningTotal.subtract(this.calculateDiscount());
    return runningTotal;
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

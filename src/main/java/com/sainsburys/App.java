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

  public BigDecimal total() {
    BigDecimal runningTotal = new BigDecimal("0");
    for (String product : this.products) {
      runningTotal = runningTotal.add(this.priceForProduct(product));
    }
    return runningTotal;
  }

  private BigDecimal priceForProduct(String product) {
    if (product.equals("heart")) {
      return new BigDecimal("9.25");
    } else if (product.equals("cufflinks")) {
      return new BigDecimal("45.00");
    } else {
      throw new RuntimeException("Product " + product + " does not have a price.");
    }
  }
}

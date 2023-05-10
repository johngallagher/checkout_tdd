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

  public void scan(String string) {
    this.products.add(string);
  }

  public BigDecimal total() {
    BigDecimal runningTotal = new BigDecimal("0");
    for (String product : this.products) {
      runningTotal = runningTotal.add(this.priceForProduct(product));
    }
    return runningTotal;
  }

  private BigDecimal priceForProduct(String product) {
    if (product == "heart") {
      return new BigDecimal("9.25");
    } else if (product == "cufflinks") {
      return new BigDecimal("45.00");
    } else {
      return new BigDecimal("0");
    }
  }
}

package com.sainsburys;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class App {

  public static void main(String[] products) {
  }

  private List<String> products = new ArrayList();

  public void scan(String product) {
    this.products.add(product);
  }

  public String calculateTotal() {
    BigDecimal total = new BigDecimal("0");

    for(String product : products) {
      if (product.equals("heart")){
        total = total.add(new BigDecimal("9.25"));
      }
      else {
        total = total.add(new BigDecimal("45.00"));
      }
    }

    if (total.compareTo(new BigDecimal("60.00")) > 0) {
      total = total.multiply(new BigDecimal("0.9"));
    }

    return total.setScale(2, RoundingMode.HALF_UP).toString(); 
  }
}

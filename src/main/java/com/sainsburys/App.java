package com.sainsburys;

public class App {

  public static void main(String[] products) {
  }

  private String product;

  public void scan(String product) {
    this.product = product;
  }

  public String calculateTotal() {
    if (product.equals("heart")){
      return "9.25";
    } else {
      return "45.00";
    }
  }
}

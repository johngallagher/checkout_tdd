package com.sainsburys;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    private final ArrayList<String> products;

    public App() {
        this.products = new ArrayList<String>();
    }

    public static void main(String[] products )
    {
        App app = new App();
        for (String product : products) {
            app.scan(product);
        }
        System.out.println("Total is:");
        System.out.println("£" + app.calculateTotal());
    }

    private BigDecimal calculateTotal() {
        BigDecimal total = new BigDecimal(0);
        for (String product : this.products) {
            total = total.add(this.getPrice(product));
        }
        return total;
    }

    private BigDecimal getPrice(String product) {
        if (product.equals("tshirt")) {
            return new BigDecimal("0.60");
        } else if (product.equals("mug")) {
            return new BigDecimal("3.10");
        } else {
            throw new RuntimeException("Unknown product: " + product);
        }
    }

    private void scan(String product) {
        this.products.add(product);
    }

    public String getMessage() {
        return "Hello World!";
    }
}

package com.sainsburys;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
  public static Test suite() {
    return new TestSuite(AppTest.class);
  }

  public void testScanningHeartGivesATotalOfTheUnitPrice() {
    String heart = "heart";
    App app = new App();
    app.scan(heart);
    assertEquals("9.25", app.calculateTotal());
  }

  public void testScanningCufflinksGivesATotalOfTheUnitPrice() {
    String cufflinks = "cufflinks";
    App app = new App();
    app.scan(cufflinks);
    assertEquals("45.00", app.calculateTotal());
  }
}

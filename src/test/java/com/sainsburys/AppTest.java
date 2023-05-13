package com.sainsburys;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
  public static Test suite() {
    return new TestSuite(AppTest.class);
  }

  public void testCheckingOutWithASingleHeartGivesATotalOfTheUnitPrice() {
    String heart = "heart";
    App app = new App();
    app.scan(heart);
    assertEquals("9.25", app.calculateTotal());
  }
}

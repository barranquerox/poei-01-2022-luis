package pageobjects.amazon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationAddToCartPage {
  WebDriver driver;
  Logger log = LogManager.getLogger(ConfirmationAddToCartPage.class);

  By cartButtonSelector = By.id("nav-cart");

  public ConfirmationAddToCartPage(WebDriver driver) {
    this.driver = driver;
  }

  public CartPage openCart() {
    log.info("Open cart");
    driver.findElement(cartButtonSelector).click();
    log.info("Click open cart button");

    CartPage cartPage = new CartPage(driver);
    return cartPage;
  }
}

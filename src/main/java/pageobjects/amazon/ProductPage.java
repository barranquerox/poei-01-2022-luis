package pageobjects.amazon;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
  WebDriver driver;

  Logger log = LogManager.getLogger(ProductPage.class);

  int timeoutSidebar = 3;
  int timeoutConfirmation = 10;

  By addToCartButtonSelector = By.id("add-to-cart-button");
  By addCoverageButtonSelector = By.cssSelector("#attachSiAddCoverage input");
  By noCoverageButtonSelector = By.cssSelector("#attachSiNoCoverage input");
  By confirmationImageSelector = By.id("add-to-cart-confirmation-image");

  public ProductPage(WebDriver driver) {
    this.driver = driver;
  }

  public ProductPage addToCart() {
    log.info("Add to cart");
    driver.findElement(addToCartButtonSelector).click();
    log.info("Click add to cart button");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSidebar));
    wait.until(ExpectedConditions.elementToBeClickable(addCoverageButtonSelector));
    log.info("Add coverage (warranty) button is clickable");
    return this;
  }

  public ConfirmationAddToCartPage noCoverage() {
    log.info("No Coverage");
    driver.findElement(noCoverageButtonSelector).click();
    log.info("Click no coverage button");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutConfirmation));
    wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationImageSelector));
    log.info("Confirmation image is displayed");

    ConfirmationAddToCartPage confirmationAddToCartPage = new ConfirmationAddToCartPage(driver);
    return confirmationAddToCartPage;
  }
}

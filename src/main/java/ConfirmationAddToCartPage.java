import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationAddToCartPage {
  WebDriver driver;

  By cartButtonSelector = By.id("nav-cart");

  public ConfirmationAddToCartPage(WebDriver driver) {
    this.driver = driver;
  }

  public void openCart() {
    driver.findElement(cartButtonSelector).click();
  }
}

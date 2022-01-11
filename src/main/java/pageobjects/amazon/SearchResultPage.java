package pageobjects.amazon;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultPage {
  WebDriver driver;

  By searchResultSelector = By.cssSelector("[data-component-type='s-search-result']");

  public SearchResultPage(WebDriver driver) {
    this.driver = driver;
  }

  public ProductPage openResult(int index) {
    List<WebElement> listOfResults = driver.findElements(searchResultSelector);
    listOfResults.get(index).click();

    ProductPage productPage = new ProductPage(driver);
    return productPage;
  }
}

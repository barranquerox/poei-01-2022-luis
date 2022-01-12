package pageobjects.amazon;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultPage {
  WebDriver driver;
  Logger log = LogManager.getLogger(SearchResultPage.class);

  By searchResultSelector = By.cssSelector("[data-component-type='s-search-result']");

  public SearchResultPage(WebDriver driver) {
    this.driver = driver;
  }

  public ProductPage openResult(int index) {
    log.info("Open result number {}", index);

    List<WebElement> listOfResults = driver.findElements(searchResultSelector);
    listOfResults.get(index).click();
    log.info("Click result number {}", index);

    ProductPage productPage = new ProductPage(driver);
    return productPage;
  }
}

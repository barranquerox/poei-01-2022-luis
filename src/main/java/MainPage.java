import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
  WebDriver driver;

  int timeoutSearch = 10;

  By searchBarSelector = By.id("twotabsearchtextbox");
  By searchResultSelector = By.cssSelector("[data-component-type='s-search-result']");

  public MainPage(WebDriver driver) {
    this.driver = driver;
  }

  public SearchResultPage searchProduct(String productName) {
    // Trouver l'element et interagir avec l'element
    driver.findElement(searchBarSelector).sendKeys(productName + Keys.ENTER);

    // attendre que l'action soit fini
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSearch));
    wait.until(ExpectedConditions.elementToBeClickable(searchResultSelector));

    SearchResultPage searchResultPage = new SearchResultPage(driver);
    return searchResultPage;
  }
}

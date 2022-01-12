import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.amazon.CartPage;
import pageobjects.amazon.MainPage;

public class AmazonTest {

  WebDriver driver;

  @BeforeMethod
  public void setup() {
//    URL seleniumGridUrl = null;
//    try {
//      seleniumGridUrl = new URL("http://localhost:4444");
//    } catch (MalformedURLException e) {
//      e.printStackTrace();
//    }
//    ChromeOptions chromeOptions = new ChromeOptions();
//    driver = new RemoteWebDriver(seleniumGridUrl, chromeOptions);
    driver = new ChromeDriver();
    driver.get("https://www.amazon.fr");
    driver.manage().window().maximize();

    // fermer cookies
    driver.findElement(By.id("sp-cc-accept")).click();
  }

  @AfterMethod
  public void teardown() {
    driver.quit();
  }

  @Test
  public void hpChromebookAddToCartPriceTest() {
    // Arrange
    String productName = "HP Chromebook x360 14a-ca0000sf";
    String expectedPrice = "369,00 €";

    // Act
    MainPage mainPage = new MainPage(driver);
    CartPage cartPage = mainPage.searchProduct(productName)
        .openResult(0)
        .addToCart()
        .noCoverage()
        .openCart();

    // Assert
    Assert.assertEquals(cartPage.getProductPrice(0), expectedPrice);
    Assert.assertEquals(cartPage.getActiveCartSubtotal(), expectedPrice);
    Assert.assertEquals(cartPage.getBuyboxCartSubtotal(), expectedPrice);
  }

  @Test
  public void passedTest() {
    MainPage mainPage = new MainPage(driver);

    Assert.assertTrue(true);
  }

  @Test
  public void hoverTest() {
    By buttonSelector = By.id("nav-link-accountList");
    WebElement button = driver.findElement(buttonSelector);
    Actions hover = new Actions(driver);
    hover.moveToElement(button);
    hover.perform();

    By myAccountLinkSelector = By.cssSelector("#nav-al-your-account .nav-title + a");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    wait.until(ExpectedConditions.elementToBeClickable(myAccountLinkSelector)).click();

  }
}

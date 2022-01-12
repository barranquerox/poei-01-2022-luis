import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
  Logger log = LogManager.getLogger(AmazonTest.class);

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
    log.debug("Chrome has started");

    driver.get("https://www.amazon.fr");
    log.info("Amazon Home page is opened");

    driver.manage().window().maximize();
    log.trace("The window is maximized");

    // FATAL > ERROR > WARN > INFO > DEBUG > TRACE

    // fermer cookies
    driver.findElement(By.id("sp-cc-accept")).click();
    log.info("Closed cookie window");
  }

  @AfterMethod
  public void teardown() {
    driver.quit();
    log.debug("Chrome was closed");
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
    WebElement myAccountButton;

    try {
      myAccountButton = driver.findElement(buttonSelector);
    }
    catch (NoSuchElementException e) {
      log.error("The button my account was not found", e);
      throw e;
    }

    log.debug("The button my account was found");

    Actions hover = new Actions(driver);
    hover.moveToElement(myAccountButton);
    hover.perform();
    log.info("Mouse hover button");

    By myAccountLinkSelector = By.cssSelector("#nav-al-your-account .nav-title + a");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

    WebElement myAccountLink = wait.until(ExpectedConditions.elementToBeClickable(myAccountLinkSelector));
    log.info("The account button was found");
    myAccountLink.click();
    log.info("The account button was clicked");


  }
}

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UberEatsTests {

  WebDriver driver;

  @BeforeMethod
  public void setup() {
    driver = new ChromeDriver();

    driver.get("https://www.ubereats.com/fr");
    driver.manage().window().maximize();

    By buttonCookieSelector = By.cssSelector("#cookie-banner button.bj");
    driver.findElement(buttonCookieSelector).click();
  }

  @Test
  public void addBurgerToCart() {
    // Arrange
    By addressInputSelector = By.id("location-typeahead-home-input");
    By autocompleteSelector = By.cssSelector("#location-typeahead-home-menu > li");
    By categoryBurguerSelector = By.cssSelector("img[alt='Burgers']");
    By kingMarcelRestaurant = By.cssSelector("a[href='/fr/store/king-marcel-nanterre/07TTIgUiQPWyz4uq4_H35w'] > h3");
    By foodItemSelector = By.cssSelector("div[tabindex='0']");
    By addToCartButtonSelector =  By.cssSelector(".spacer._24 + button.b8");
    By cartSelector = By.cssSelector("button[aria-label='checkout'] > div");
    String address = "La Defense";
    String expectedCartItems = "1";

    // Act
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    driver.findElement(addressInputSelector).sendKeys(address);
    wait.until(ExpectedConditions.visibilityOfElementLocated(autocompleteSelector));
    driver.findElement(addressInputSelector).sendKeys(Keys.ENTER);

    wait.until(ExpectedConditions.elementToBeClickable(categoryBurguerSelector));
    driver.findElement(categoryBurguerSelector).click();

    wait.until(ExpectedConditions.elementToBeClickable(kingMarcelRestaurant));
    driver.findElement(kingMarcelRestaurant).click();

    wait.until(ExpectedConditions.elementToBeClickable(foodItemSelector));
    driver.findElements(foodItemSelector).get(0).click();

    wait.until(ExpectedConditions.elementToBeClickable(addToCartButtonSelector));
    driver.findElement(addToCartButtonSelector).click();

    wait.until(ExpectedConditions.invisibilityOfElementLocated(addToCartButtonSelector));

    // Assert
    WebElement cart = driver.findElement(cartSelector);
    Assert.assertTrue(cart.getText().contains(expectedCartItems), "The cart does not contain 1 item");
  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
}

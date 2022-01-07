import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmazonTest {

  WebDriver driver;

  @BeforeMethod
  public void setup() {
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
  public void laptopAddToCartPriceTest() {
    // Arrange
    String productName = "HP Chromebook x360 14a-ca0000sf";
    String expectedPrice = "369,00 €";

    // Act
    MainPage mainPage = new MainPage(driver);
    mainPage.searchProduct(productName);

    SearchResultPage searchResultPage = new SearchResultPage(driver);
    searchResultPage.openResult(0);

    ProductPage productPage = new ProductPage(driver);
    productPage.addToCart();
    productPage.noCoverage();

    ConfirmationAddToCartPage confirmationAddToCartPage = new ConfirmationAddToCartPage(driver);
    confirmationAddToCartPage.openCart();

    CartPage cartPage = new CartPage(driver);
    String productPrice = cartPage.getProductPrice(0);
    String activeCartSubtotal = cartPage.getActiveCartSubtotal();
    String buyboxCartSubtotal = cartPage.getBuyboxCartSubtotal();

    // Assert
    Assert.assertEquals(productPrice, expectedPrice);
    Assert.assertEquals(activeCartSubtotal, expectedPrice);
    Assert.assertEquals(buyboxCartSubtotal, expectedPrice);
  }
}

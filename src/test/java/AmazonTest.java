import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
  public void nintendoSwitchAddToCartPriceTest() {
    // Arrange
    String productName = "Nintendo Switch Oled";

    // Act
    MainPage mainPage = new MainPage(driver);
    mainPage.searchProduct(productName);

    SearchResultPage searchResultPage = new SearchResultPage(driver);
    searchResultPage.openResult(1);

    ProductPage productPage = new ProductPage(driver);
    productPage.addToCart();
    productPage.noCoverage();

    // Assert
  }

  @Test
  public void machineARacletteTest() {
    // Arrange
    String productName = "Machine a raclette";

    // Act
    MainPage mainPage = new MainPage(driver);
    mainPage.searchProduct(productName);

    SearchResultPage searchResultPage = new SearchResultPage(driver);
    searchResultPage.openResult(5);
  }
}

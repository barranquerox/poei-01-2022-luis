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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class tp1 {
  WebDriver driver;

  @BeforeMethod
  public void setup() {
    driver = new ChromeDriver();

    driver.get("https://www.amazon.fr");
    driver.manage().window().maximize();
    // fermer cookies
    WebElement buttonCookies = driver.findElement(By.id("sp-cc-accept"));
    buttonCookies.click();
  }

  @AfterMethod
  public void teardown() {
    driver.quit();
  }

  // chercher machine a raclette dans la barre de recherche amazon
  @Test
  public void test1() {
    WebElement barreRecherche = driver.findElement(By.id("twotabsearchtextbox"));
    barreRecherche.sendKeys("machine a raclette");
    barreRecherche.sendKeys(Keys.ENTER);
  }

  @Test
  public void test2() {


    WebElement barreRecherche = driver.findElement(By.id("twotabsearchtextbox"));
    barreRecherche.sendKeys("machine a raclette");
    barreRecherche.sendKeys(Keys.ENTER);

    // IMPLICIT WAIT ICI

    WebElement premierResultat = driver.findElement(By.cssSelector("[data-cel-widget='search_result_1']"));
    premierResultat.click();
    WebElement ajouterPanierBouton = driver.findElement(By.cssSelector("#add-to-cart-button"));
    ajouterPanierBouton.click();
  }

  @Test
  public void testExplicitWait() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    driver.findElement(By.id("nav-hamburger-menu")).click();

    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".hmenu-item[data-menu-id='10']")));
    driver.findElement(By.cssSelector(".hmenu-item[data-menu-id='10']")).click();

    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("ul.hmenu-visible > li:nth-child(3)")));
    driver.findElement(By.cssSelector("ul.hmenu-visible > li:nth-child(3) > a")).click();
  }

  @Test
  public void testMultipleElements() {
    // Arrange Act Assert
    // Arrange
    int expectedNumberOfResults = 60;
    String keyword = "machine a raclette";
    int timeoutSearchLoad = 10;
    By searchBarSelector = By.id("twotabsearchtextbox");
    By searchResultSelector = By.cssSelector("[data-component-type='s-search-result']");


    // Act
    WebElement barreRecherche = driver.findElement(searchBarSelector);
    barreRecherche.sendKeys( keyword + Keys.ENTER);

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSearchLoad));
    wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultSelector));

    List<WebElement> listeDeResultat = driver.findElements(searchResultSelector);

    // Assert
    Assert.assertEquals(listeDeResultat.size(), expectedNumberOfResults, "The number of search results is not correct");
  }
}

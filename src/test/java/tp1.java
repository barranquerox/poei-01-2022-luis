import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    WebElement premierResultat = driver.findElement(By.cssSelector("[data-cel-widget='search_result_1']"));
    premierResultat.click();
    WebElement ajouterPanierBouton = driver.findElement(By.cssSelector("#add-to-cart-button"));
    ajouterPanierBouton.click();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

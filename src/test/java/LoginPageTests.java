import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginPageTests {

    public static WebDriver driver;

    @BeforeClass
    public void setUp(){
        ChromeDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void testLoginPage(){
        WebElement loginButton = driver.findElement(By.id("login-button"));
        Assert.assertEquals(loginButton.isDisplayed(),true);
    }

    @Test
    public void testSuccessfulLogin(){
        WebElement loginButton = driver.findElement(By.id("login-button"));
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();
        WebElement productTitle = driver.findElement(By.xpath("//span[@class='title']"));
        Assert.assertEquals(productTitle.getText(),"PRODUCTS");
    }

    @AfterClass
    public void tearDown(){
        driver.close();
    }
}

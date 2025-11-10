package tests;

import Utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class LoginValidTest {
    WebDriver driver;

    //@BeforeMethod
    public void setUp() {
        System.setProperty(
                "webdriver.chrome.driver", "D:/IDEA/Dibimbing-selenium/src/test/java/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver();
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
    }
    // Test data login
    @Test
    public void testValidLogin() {
        WebDriver driver = DriverManager.getDriver();
        driver.get("https://resonance.dibimbing.id/login"); // input URL

        // element field username dan password
        WebElement username = driver.findElement(By.xpath("//input[@id='input-email-login']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='input-password-login']"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@id='btn-login']"));
        // Input valid credential
        username.sendKeys("adrynivannatoban@gmail.com");
        password.sendKeys("231966#");
        loginButton.click();

        // Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dashboardText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//p[@class='chakra-text css-oqyjp1']"))
        );
        // success verify
        Assert.assertTrue(dashboardText.isDisplayed(), "Failed Login");
    }

    //@AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

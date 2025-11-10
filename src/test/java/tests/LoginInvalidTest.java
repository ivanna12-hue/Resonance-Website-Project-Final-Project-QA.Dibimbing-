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


public class LoginInvalidTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/IDEA/Dibimbing-selenium/src/test/java/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver();
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
    }

    // Test data login
    @Test
    public void testInvalidLogin() {
        driver.get("https://resonance.dibimbing.id/login"); // input URL
        // element field username dan password
        WebElement username = driver.findElement(By.xpath("//input[@id='input-email-login']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='input-password-login']"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@id='btn-login']"));
        // Input kredensial invalid
        username.sendKeys("adrynivannatoban@gmail.com");
        password.sendKeys("231966#1"); // input wrong password
        loginButton.click();
        // Tunggu sejenak
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//*[text()='Invalid Credentials']")));
        // Verifikasi hasil login
        WebElement welcomeText = driver.findElement(By.xpath("//*[text()='Invalid Credentials']"));
        String actualText = welcomeText.getText();
        Assert.assertTrue(actualText.contains("Invalid Credentials"), "Success Login");
        //Input delete username dan delete password
        username.clear();
        password.clear();
    }
    //@AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

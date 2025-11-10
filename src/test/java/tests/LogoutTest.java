package tests;

import Utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.time.Duration;

public class LogoutTest {
    WebDriver driver;
    WebDriverWait wait;

    //@BeforeMethod
    public void setUp() {
        // Set lokasi ChromeDriver
        System.setProperty("webdriver.chrome.driver",
                "D:/IDEA/Dibimbing-selenium/src/test/java/drivers/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 🔹 Step: Login dulu sebelum logout
        driver.get("https://resonance.dibimbing.id/login");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email-login")));
        WebElement password = driver.findElement(By.id("input-password-login"));
        WebElement loginButton = driver.findElement(By.id("btn-login"));

        username.sendKeys("adrynivannatoban@gmail.com");
        password.sendKeys("231966#");
        loginButton.click();

        // Tunggu sampai halaman dashboard tampil
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div/div[1]/p")));

    }

    @Test
    public void testLogout()  {
        WebDriver driver= DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        // Klik "<" menu sidebar
        WebElement openMenuButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//button[@id='btn-open-navbar']")));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        openMenuButton.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        // Tunggu menu muncul ">"
        WebElement openMenuButton2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='btn-close-navbar']")));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        openMenuButton2.isDisplayed();

        // Tunggu menu muncul dan klik Logout
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='btn-logout']")));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        logoutButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        // Verifikasi kembali ke halaman login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btn-login']")));
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
        if (driver != null) {
            driver.quit();
        }
    }
}

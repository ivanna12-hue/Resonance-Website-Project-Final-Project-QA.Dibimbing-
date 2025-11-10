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

public class createTicket {
    WebDriver driver;
    WebDriverWait wait;

    //@BeforeMethod
    public void setUp() {
        // Set lokasi ChromeDriver
        System.setProperty("webdriver.chrome.driver",
                "D:/IDEA/Dibimbing-selenium/src/test/java/drivers/chromedriver.exe");

        // Inisialisasi Chrome
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Step 1: Login account first
        driver.get("https://resonance.dibimbing.id/login");

        WebElement username = driver.findElement(By.id("input-email-login"));
        WebElement password = driver.findElement(By.id("input-password-login"));
        WebElement loginButton = driver.findElement(By.id("btn-login"));

        username.sendKeys("adrynivannatoban@gmail.com");
        password.sendKeys("231966#");
        loginButton.click();

        // Tunggu dashboard muncul
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[@class='chakra-text css-oqyjp1']"))
        );
    }

    @Test
    public void testCreateTicket() throws InterruptedException {
        WebDriver driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Step 2: Klik tombol Create Ticket
        WebElement createTicketButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@id='btn-create-ticket']")));
        createTicketButton.click();

        // Tunggu form “Describe Your Issue” muncul
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//textarea[@id='textarea-ticket-description']"))
        );

        //Step 3: Isi title
        WebElement titleField = driver.findElement(By.xpath("//input[@id='input-ticket-title']"));
        titleField.sendKeys("Login failed2");

        //Step 4: Isi description
        WebElement descriptionField = driver.findElement(By.xpath("//textarea[@id='textarea-ticket-description']"));
        descriptionField.sendKeys("Password is correct but login still fails.");

        //Step 5: click button Submit Ticket
        WebElement submitButton = driver.findElement(By.xpath("//button[@id='btn-submit-ticket']"));
        submitButton.click();

        //Step 6: success verify
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[text()='Berhasil Membuat Ticket Issue']")));
        Assert.assertTrue(successMsg.isDisplayed(), "Tidak Berhasil Membuat Ticket Issue");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btn-dashboard']")));
    }

    //@AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

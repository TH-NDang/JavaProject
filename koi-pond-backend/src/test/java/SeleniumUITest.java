import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumUITest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/login");
    }

    @Test
    public void testLogin() throws InterruptedException {
        WebElement email = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));

        email.sendKeys("admin@gmail.com");
        password.sendKeys("admin123");

        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(3000);

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "http://localhost:3000/admin/dashboard";
        Assert.assertEquals(expectedUrl, actualUrl);

        String actualText = driver.findElement(By.cssSelector("h1")).getText();
        String expectedText = "Dashboard";
        assertEquals(expectedText, actualText);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

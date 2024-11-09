import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumUITest {

    @Test
    public void testLogin() {
        // Cấu hình đường dẫn tới chromedriver
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Cấu hình đường dẫn tới geckodriver
        // System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
        // Khởi tạo WebDriver cho Firefox
        // WebDriver driver = new FirefoxDriver();

        // Cấu hình đường dẫn tới msedgedriver
        // System.setProperty("webdriver.edge.driver", "/usr/bin/msedgedriver");
        // Khởi tạo WebDriver cho Edge
        // WebDriver driver = new EdgeDriver();

        driver.get("http://localhost:3000/login");

        driver.findElement(By.id("email")).sendKeys("admin@gmail.com");
        driver.findElement(By.id("password")).sendKeys("admin123");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Chờ 1 phút để trang tải và kiểm tra kết quả
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}

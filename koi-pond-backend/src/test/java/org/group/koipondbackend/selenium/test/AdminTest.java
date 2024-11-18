package org.group.koipondbackend.selenium.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.group.koipondbackend.selenium.config.ChromeConfig;
import org.group.koipondbackend.selenium.pages.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private ServicesPage servicesPage;
    private UsersPage usersPage;

    @BeforeAll
    public void setUp() {
        // driver = new ChromeDriver();
        ChromeOptions options = ChromeConfig.getOptions();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        loginPage = new LoginPage(driver, wait);
        servicesPage = new ServicesPage(driver, wait);
        usersPage = new UsersPage(driver, wait);

        // Set slow mode for all pages
        loginPage.setSlowMode(true);
        servicesPage.setSlowMode(true);
        usersPage.setSlowMode(true);

        // Đăng nhập với tài khoản admin
        driver.get("http://localhost:3000/login");
        loginPage.login("admin@gmail.com", "admin123");
    }

    @Test
    @DisplayName("Should create new service successfully")
    public void testCreateService() {
        servicesPage.navigateToServices();
        servicesPage.openCreateServiceForm();

        // Tạo tên dịch vụ duy nhất bằng cách thêm timestamp
        String uniqueServiceName = "Test Service " + generateUniqueId();

        servicesPage.fillServiceForm(
                uniqueServiceName,
                "Test Description",
                "1000000",
                new String[] { "Feature 1", "Feature 2" },
                true);

        Assertions.assertTrue(servicesPage.isSuccessMessageDisplayed());
        Assertions.assertTrue(servicesPage.isServiceDisplayed(uniqueServiceName));
    }

    @Test
    @DisplayName("Should show error when creating duplicate service")
    public void testCreateDuplicateService() {
        servicesPage.navigateToServices();

        // Tạo dịch vụ đầu tiên
        String serviceName = "Duplicate Test Service " + generateUniqueId();
        servicesPage.openCreateServiceForm();
        servicesPage.fillServiceForm(
                serviceName,
                "Test Description",
                "1000000",
                new String[] { "Feature 1" },
                false);

        // Thử tạo dịch vụ trùng tên
        servicesPage.openCreateServiceForm();
        servicesPage.fillServiceForm(
                serviceName,
                "Another Description",
                "2000000",
                new String[] { "Feature 2" },
                false);

        Assertions.assertTrue(servicesPage.isDuplicateErrorDisplayed());
    }

    private String generateUniqueId() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    @Test
    @DisplayName("Should manage users successfully")
    public void testUserManagement() {
        usersPage.navigateToUsers();
        String timestamp = generateUniqueId();
        String userEmail = "test" + timestamp + "@example.com";
        String userName = "Test User " + timestamp;

        usersPage.openCreateUserForm();
        usersPage.fillUserForm(
                userName,
                userEmail,
                "password123",
                "0987654321",
                "123 Test Street",
                "CUSTOMER");
        Assertions.assertTrue(usersPage.isSuccessMessageDisplayed());

        usersPage.searchUser(userEmail);
        Assertions.assertTrue(usersPage.isUserDisplayed(userName));
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

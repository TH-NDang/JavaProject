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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private ContactPage contactPage;

    @BeforeAll
    public void setUp() {
        ChromeOptions options = ChromeConfig.getOptions();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        loginPage = new LoginPage(driver, wait);
        dashboardPage = new DashboardPage(driver, wait);
        contactPage = new ContactPage(driver, wait);

        // Set slow mode for all pages
        loginPage.setSlowMode(true);
    }

    @BeforeEach
    public void navigateToLoginPage() {
        driver.get("http://localhost:3000/login");
    }

    @Test
    @DisplayName("Should login successfully with admin credentials")
    public void testAdminLogin() {
        loginPage.login("admin@gmail.com", "admin123");
        Assertions.assertTrue(dashboardPage.isDashboardVisible());
        Assertions.assertEquals("Tổng quan", dashboardPage.getDashboardTitle());
    }

    @Test
    @DisplayName("Should show error message with invalid credentials")
    public void testInvalidLogin() {
        loginPage.login("invalid@email.com", "wrongpassword");
        Assertions.assertTrue(loginPage.isErrorMessageDisplayed());
        Assertions.assertEquals("Invalid email or password", loginPage.getErrorMessage());
    }

    @Test
    @DisplayName("Should submit contact form successfully")
    public void testContactFormSubmission() {
        driver.get("http://localhost:3000/contact");

        contactPage.fillContactForm(
                "Test User",
                "test@email.com",
                "0123456789",
                "design",
                "Test message content");

        Assertions.assertTrue(contactPage.isSuccessMessageDisplayed());
    }

    @Test
    @DisplayName("Should validate required fields in contact form")
    public void testContactFormValidation() {
        driver.get("http://localhost:3000/contact");

        contactPage.submitEmptyForm();
        Assertions.assertTrue(contactPage.areValidationErrorsDisplayed());
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

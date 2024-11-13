package org.group.koipondbackend.selenium.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.group.koipondbackend.selenium.pages.*;
import org.group.koipondbackend.selenium.utils.TestHelper;
import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SystemDemoTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private TestHelper helper;

    private HomePage homePage;
    private AboutPage aboutPage;
    private ServicesPage servicesPage;
    private ContactPage contactPage;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeAll
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        // driver = new ChromeDriver(options);
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper = new TestHelper(driver);

        homePage = new HomePage(driver, wait);
        aboutPage = new AboutPage(driver, wait);
        servicesPage = new ServicesPage(driver, wait);
        contactPage = new ContactPage(driver, wait);
        loginPage = new LoginPage(driver, wait);
        dashboardPage = new DashboardPage(driver, wait);
    }

    @Test
    @DisplayName("Complete System Demo")
    public void testFullSystemDemo() {
        // Part 1: Public Pages Demo
        demonstratePublicPages();

        // Part 2: Admin System Demo
        demonstrateAdminSystem();
    }

    private void demonstratePublicPages() {
        // Home Page Demo
        homePage.visitHomePage();
        homePage.demonstrateNavigation("Menu điều hướng chính của website");
        homePage.demonstrateHeroSection("Giới thiệu về dịch vụ thiết kế và thi công hồ cá Koi");
        homePage.demonstrateFeatures("Các tính năng nổi bật của dịch vụ");
        homePage.demonstrateProjects("Dự án tiêu biểu đã thực hiện");
        helper.sleep(2000);

        // Services Page Demo
        servicesPage.visitServicesPage();
        servicesPage.demonstrateServiceHero("Giới thiệu tổng quan về dịch vụ");
        servicesPage.demonstrateServicePackages("Các gói dịch vụ chi tiết");
        servicesPage.demonstrateProcess("Quy trình thực hiện dự án");
        servicesPage.demonstratePricing("Bảng giá dịch vụ");
        helper.sleep(2000);

        // About Page Demo
        aboutPage.visitAboutPage();
        aboutPage.demonstrateHero("Giới thiệu về công ty");
        aboutPage.demonstrateStory("Câu chuyện phát triển");
        aboutPage.demonstrateStats("Thống kê hoạt động");
        aboutPage.demonstrateTeam("Đội ngũ chuyên gia");
        aboutPage.demonstrateValues("Giá trị cốt lõi");
        helper.sleep(2000);

        // Contact Page Demo
        contactPage.visitContactPage();
        contactPage.demonstrateContactInfo("Thông tin liên hệ");
        contactPage.demonstrateContactForm("Form gửi yêu cầu tư vấn");
        helper.sleep(2000);
    }

    private void demonstrateAdminSystem() {
        // Login Demo
        loginPage.visitLoginPage();
        loginPage.demonstrateLoginForm("Form đăng nhập hệ thống");
        loginPage.demonstrateLogin(
                "admin@gmail.com",
                "admin123",
                "Đăng nhập với tài khoản admin");

        // Dashboard Demo
        dashboardPage.demonstrateDashboard();
        dashboardPage.demonstrateStats("Thống kê tổng quan hoạt động kinh doanh");
        dashboardPage.demonstrateCharts("Biểu đồ phân tích doanh thu và đơn hàng");
        dashboardPage.demonstrateRecentOrders("Danh sách đơn hàng gần đây");
        helper.sleep(2000);

        // Demo Other Admin Features
        helper.addAnnotation("Demo các chức năng quản trị khác");

        dashboardPage.navigateToOrders("Quản lý đơn hàng - Nơi xử lý các đơn đặt hàng");
        helper.sleep(3000);

        dashboardPage.navigateToServices("Quản lý dịch vụ - Cập nhật thông tin dịch vụ");
        helper.sleep(3000);

        dashboardPage.navigateToUsers("Quản lý người dùng - Quản lý tài khoản khách hàng và nhân viên");
        helper.sleep(3000);

        dashboardPage.navigateToReports("Báo cáo thống kê - Phân tích dữ liệu kinh doanh");
        helper.sleep(3000);
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

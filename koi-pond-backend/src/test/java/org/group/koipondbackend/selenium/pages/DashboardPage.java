package org.group.koipondbackend.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePage {
    private final By dashboardTitle = By.xpath("//h1[contains(text(),'Tổng quan')]");
    private final By statsSection = By.cssSelector("div.grid.grid-cols-1.md\\:grid-cols-2.lg\\:grid-cols-4.gap-6");
    private final By chartsSection = By.cssSelector("div.grid.lg\\:grid-cols-2.gap-6");
    private final By recentOrdersSection = By.xpath("//div[.//h3[contains(text(),'Đơn hàng gần đây')]]");
    private final By sidebar = By.cssSelector("div.bg-primary-700");
    private final By statsCards = By.cssSelector("[class*='stats-card']");

    // Sidebar menu items
    private final By ordersLink = By.xpath("//a[contains(@href,'/admin/orders')]");
    private final By servicesLink = By.xpath("//a[contains(@href,'/admin/services')]");
    private final By usersLink = By.xpath("//a[contains(@href,'/admin/users')]");
    private final By reportsLink = By.xpath("//a[contains(@href,'/admin/reports')]");

    
    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

     public void demonstrateDashboard() {
        helper.addAnnotation("Trang Tổng quan - Hiển thị tổng quan về hoạt động kinh doanh");
        helper.sleep(1500);

        // Demonstrate sidebar
        WebElement sidebarElement = driver.findElement(sidebar);
        helper.highlightSection(sidebarElement, "Menu điều hướng chính của admin");
        helper.sleep(1500);

        // Demonstrate title
        WebElement titleElement = driver.findElement(dashboardTitle);
        helper.highlightSection(titleElement, "Tiêu đề trang tổng quan");
        helper.sleep(1500);
    }

    public void demonstrateStats(String description) {
        WebElement stats = driver.findElement(statsSection);
        helper.highlightSection(stats, description);
        helper.sleep(2000);
    }

    public void demonstrateCharts(String description) {
        WebElement charts = driver.findElement(chartsSection);
        helper.highlightSection(charts, description);
        helper.sleep(2000);
    }

    public void demonstrateRecentOrders(String description) {
        WebElement recentOrders = driver.findElement(recentOrdersSection);
        helper.highlightSection(recentOrders, description);
        helper.sleep(2000);
    }

    public void navigateToOrders(String description) {
        WebElement link = driver.findElement(ordersLink);
        helper.clickWithHighlight(link, description);
    }

    public void navigateToServices(String description) {
        WebElement link = driver.findElement(servicesLink);
        helper.clickWithHighlight(link, description);
    }

    public void navigateToUsers(String description) {
        WebElement link = driver.findElement(usersLink);
        helper.clickWithHighlight(link, description);
    }

    public void navigateToReports(String description) {
        WebElement link = driver.findElement(reportsLink);
        helper.clickWithHighlight(link, description);
    }


    public boolean isDashboardVisible() {
        return isElementDisplayed(dashboardTitle);
    }

    public String getDashboardTitle() {
        return waitAndGetText(dashboardTitle);
    }

    public boolean areStatsCardsVisible() {
        return areElementsDisplayed(statsCards);
    }
}

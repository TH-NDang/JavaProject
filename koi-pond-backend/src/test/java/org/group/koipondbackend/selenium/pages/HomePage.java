package org.group.koipondbackend.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    // Locators
    private final By heroSection = By.xpath("//section[contains(@class, 'bg-gradient-to-r')]");
    private final By featuresSection = By.xpath("//section[.//h2[contains(text(),'Tại Sao Chọn Chúng Tôi')]]");
    private final By projectsSection = By.xpath("//section[.//h2[contains(text(),'Dự Án Tiêu Biểu')]]");
    private final By contactSection = By.xpath("//section[.//h2[contains(text(),'Sẵn Sàng Để Bắt Đầu')]]");
    private final By headerNav = By.cssSelector("header");
    private final By servicesLink = By.xpath("//a[contains(text(),'Dịch vụ')]");
    private final By aboutLink = By.xpath("//a[contains(text(),'Về chúng tôi')]");
    private final By contactLink = By.xpath("//a[contains(text(),'Liên hệ')]");
    private final By loginButton = By.xpath("//a[contains(@class, 'px-4') and contains(text(),'Đăng nhập')]");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void visitHomePage() {
        driver.get("http://localhost:3000");
        helper.addAnnotation("Chào mừng đến với trang chủ Koi Pond Co.");
        helper.sleep(1000);
    }

    public void demonstrateNavigation(String description) {
        WebElement nav = driver.findElement(headerNav);
        helper.highlightSection(nav, "Menu điều hướng chính");
        helper.sleep(1000);
    }

    public void demonstrateHeroSection(String description) {
        WebElement hero = driver.findElement(heroSection);
        helper.highlightSection(hero, description);
        helper.sleep(1000);
    }

    public void demonstrateFeatures(String description) {
        WebElement features = driver.findElement(featuresSection);
        helper.highlightSection(features, description);
        helper.sleep(1000);
    }

    public void demonstrateProjects(String description) {
        WebElement projects = driver.findElement(projectsSection);
        helper.highlightSection(projects, description);
        helper.sleep(1000);
    }

    public void demonstrateContact(String description) {
        WebElement contact = driver.findElement(contactSection);
        helper.highlightSection(contact, description);
        helper.sleep(1000);
    }

    public void navigateToServices() {
        WebElement link = driver.findElement(servicesLink);
        helper.clickWithHighlight(link, "Chuyển đến trang Dịch vụ");
    }

    public void navigateToAbout() {
        WebElement link = driver.findElement(aboutLink);
        helper.clickWithHighlight(link, "Chuyển đến trang Về chúng tôi");
    }

    public void navigateToContact() {
        WebElement link = driver.findElement(contactLink);
        helper.clickWithHighlight(link, "Chuyển đến trang Liên hệ");
    }

    public void navigateToLogin() {
        WebElement button = driver.findElement(loginButton);
        helper.clickWithHighlight(button, "Chuyển đến trang Đăng nhập");
    }
}

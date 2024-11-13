package org.group.koipondbackend.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By loginForm = By.tagName("form");
    private final By loginHeader = By.xpath("//h2[contains(text(),'Đăng nhập vào tài khoản')]");
    private final By homeLink = By.xpath("//a[contains(text(),'Quay lại trang chủ')]");
    private final By errorMessage = By.xpath("//div[contains(@class, 'text-red-600')]");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void login(String email, String password) {
        WebElement emailField = driver.findElement(emailInput);
        emailField.sendKeys(email);

        WebElement passwordField = driver.findElement(passwordInput);
        passwordField.sendKeys(password);

        WebElement button = driver.findElement(loginButton);
        button.click();

        helper.sleep(2000); // Wait for login process
    }

    public void visitLoginPage() {
        driver.get("http://localhost:3000/login");
        helper.addAnnotation("Trang đăng nhập - Nơi xác thực người dùng");
        helper.sleep(1500);
    }

    public void demonstrateLoginForm(String description) {
        WebElement form = driver.findElement(loginForm);
        helper.highlightSection(form, description);
        helper.sleep(1500);
    }

    public void demonstrateLogin(String email, String password, String description) {
        // Highlight form header
        WebElement header = driver.findElement(loginHeader);
        helper.highlightSection(header, "Tiêu đề form đăng nhập");
        helper.sleep(1000);

        // Enter email
        WebElement emailField = driver.findElement(emailInput);
        helper.typeWithHighlight(emailField, email, "Nhập địa chỉ email");

        // Enter password
        WebElement passwordField = driver.findElement(passwordInput);
        helper.typeWithHighlight(passwordField, password, "Nhập mật khẩu");

        // Click login button
        WebElement button = driver.findElement(loginButton);
        helper.clickWithHighlight(button, "Nhấn nút đăng nhập");

        helper.sleep(2000); // Wait for login process
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return waitAndGetText(errorMessage);
    }
}

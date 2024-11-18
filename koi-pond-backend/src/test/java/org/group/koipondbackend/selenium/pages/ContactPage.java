package org.group.koipondbackend.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactPage extends BasePage {
    private final By nameInput = By.name("name");
    private final By emailInput = By.name("email");
    private final By phoneInput = By.name("phone");
    private final By subjectSelect = By.name("subject");
    private final By messageInput = By.name("message");
    private final By submitButton = By.cssSelector("button[type='submit']");
    private final By successMessage = By.xpath("//h3[contains(text(),'Yêu cầu đã được gửi thành công!')]");
    private final By validationErrors = By.cssSelector("[class*='text-red-600']");

    private final By contactForm = By.tagName("form");
    private final By contactInfoSection = By.xpath("//div[.//h2[contains(text(),'Thông Tin Liên Hệ')]]");

    public ContactPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void fillContactForm(String name, String email, String phone, String subject, String message) {
        waitAndType(nameInput, name);
        waitAndType(emailInput, email);
        waitAndType(phoneInput, phone);
        selectByValue(subjectSelect, subject);
        waitAndType(messageInput, message);
        waitAndClick(submitButton);
    }

    public void submitContactForm(String name, String email, String phone, String subject, String message) {
        waitAndType(nameInput, name);
        waitAndType(emailInput, email);
        waitAndType(phoneInput, phone);
        selectByValue(subjectSelect, subject);
        waitAndType(messageInput, message);
        waitAndClick(submitButton);
    }

    public void visitContactPage() {
        driver.get("http://localhost:3000/contact");
        helper.addAnnotation("Trang Liên hệ - Nơi khách hàng có thể gửi yêu cầu tư vấn");
        helper.sleep(1500);
    }

    public void demonstrateContactInfo(String description) {
        WebElement info = driver.findElement(contactInfoSection);
        helper.highlightSection(info, description);
        helper.sleep(1500);
    }

    public void demonstrateContactForm(String description) {
        WebElement form = driver.findElement(contactForm);
        helper.highlightSection(form, "Form liên hệ");
        helper.sleep(1500);

        // Demo form fields
        WebElement nameField = driver.findElement(nameInput);
        helper.typeWithHighlight(nameField, "Nguyễn Văn A", "Nhập họ tên");

        WebElement emailField = driver.findElement(emailInput);
        helper.typeWithHighlight(emailField, "nguyenvana@email.com", "Nhập địa chỉ email");

        WebElement phoneField = driver.findElement(phoneInput);
        helper.typeWithHighlight(phoneField, "0987654321", "Nhập số điện thoại");

        WebElement subjectField = driver.findElement(subjectSelect);
        helper.clickWithHighlight(subjectField, "Chọn chủ đề liên hệ");

        WebElement messageField = driver.findElement(messageInput);
        helper.typeWithHighlight(messageField, "Tôi muốn tư vấn về thiết kế hồ cá Koi", "Nhập nội dung tin nhắn");

        WebElement submitBtn = driver.findElement(submitButton);
        helper.highlightElement(submitBtn);
        helper.sleep(1000);
    }

    public void submitEmptyForm() {
        waitAndClick(submitButton);
    }

    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(successMessage);
    }

    public boolean areValidationErrorsDisplayed() {
        return areElementsDisplayed(validationErrors);
    }
}

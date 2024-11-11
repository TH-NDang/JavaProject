package org.group.koipondbackend.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

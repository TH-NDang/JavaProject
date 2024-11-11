package org.group.koipondbackend.selenium.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ServicesPage extends BasePage {
    private final By createServiceButton = By.xpath("//button[contains(text(),'Thêm Dịch vụ')]");
    private final By nameInput = By.name("name");
    private final By descriptionInput = By.tagName("textarea");
    private final By priceInput = By.name("price");
    private final By featureInput = By.name("features.0");
    private final By addFeatureButton = By.xpath("//button[contains(text(),'Thêm tính năng')]");
    private final By popularCheckbox = By.name("isPopular");
    private final By submitButton = By.xpath("//button[contains(text(),'Tạo mới')]");
    private final By successMessage = By.xpath("//div[contains(text(),'thành công')]");
    private final By errorMessage = By
            .xpath("//div[contains(@class, 'text-red-600') or contains(text(), 'Dịch vụ với tên này đã tồn tại')]");

    public ServicesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void navigateToServices() {
        driver.get("http://localhost:3000/admin/services");
    }

    public void openCreateServiceForm() {
        waitAndClick(createServiceButton);
    }

    public void fillServiceForm(String name, String description, String price, String[] features, boolean isPopular) {
        // Nhập thông tin cơ bản
        waitAndType(nameInput, name);
        waitAndType(descriptionInput, description);
        waitAndType(priceInput, price);

        // Nhập các tính năng
        for (int i = 0; i < features.length; i++) {
            if (i > 0) { // Click thêm tính năng cho tất cả trừ tính năng đầu tiên
                waitAndClick(addFeatureButton);
            }
            By currentFeatureInput = By.name("features." + i);
            waitAndType(currentFeatureInput, features[i]);
        }

        if (isPopular) {
            waitAndClick(popularCheckbox);
        }

        waitAndClick(submitButton);
    }

    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(successMessage);
    }

    public boolean isDuplicateErrorDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public boolean isServiceDisplayed(String serviceName) {
        By serviceTitle = By.xpath(String.format("//h3[contains(text(),'%s')]", serviceName));
        return isElementDisplayed(serviceTitle);
    }
}

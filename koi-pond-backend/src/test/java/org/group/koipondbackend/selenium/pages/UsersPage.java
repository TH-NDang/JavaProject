package org.group.koipondbackend.selenium.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UsersPage extends BasePage {
    private final By createUserButton = By.xpath("//button[contains(text(),'Thêm người dùng')]");
    private final By fullNameInput = By.name("fullName");
    private final By emailInput = By.name("email");
    private final By passwordInput = By.name("password");
    private final By phoneInput = By.name("phone");
    private final By addressInput = By.name("address");
    private final By roleSelect = By.name("role");
    private final By submitButton = By.xpath("//button[contains(text(),'Thêm') and @type='submit']");
    private final By searchInput = By.xpath("//input[contains(@placeholder,'Tìm kiếm')]");
    private final By successMessage = By.xpath("//div[contains(text(),'User created successfully')]");

    public UsersPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void navigateToUsers() {
        driver.get("http://localhost:3000/admin/users");
    }

    public void openCreateUserForm() {
        waitAndClick(createUserButton);
    }

    public void fillUserForm(String fullName, String email, String password, String phone, String address,
            String role) {
        waitAndType(fullNameInput, fullName);
        waitAndType(emailInput, email);
        waitAndType(passwordInput, password);
        waitAndType(phoneInput, phone);
        waitAndType(addressInput, address);
        selectByValue(roleSelect, role);
        waitAndClick(submitButton);
    }

    public void searchUser(String keyword) {
        waitAndType(searchInput, keyword);
    }

    public boolean isUserDisplayed(String name) {
        By userRow = By.xpath(String.format("//td[contains(text(),'%s')]", name));
        return isElementDisplayed(userRow);
    }

    public void updateUserStatus(String name, String newStatus) {
        // Tìm nút thay đổi trạng thái dựa trên tên người dùng
        By userStatusButton = By.xpath(
                String.format("//td[contains(text(),'%s')]/..//button[contains(text(),'%s')]",
                        name,
                        newStatus.equals("ACTIVE") ? "Kích hoạt" : "Vô hiệu hóa"));
        waitAndClick(userStatusButton);
    }

    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(successMessage);
    }

    public boolean isStatusUpdateSuccessful() {
        return isElementDisplayed(successMessage);
    }

    // Thêm phương thức kiểm tra lỗi email trùng nếu cần
    public boolean isDuplicateEmailErrorDisplayed() {
        By errorMessage = By.xpath("//p[contains(text(),'Email đã tồn tại')]");
        return isElementDisplayed(errorMessage);
    }
}

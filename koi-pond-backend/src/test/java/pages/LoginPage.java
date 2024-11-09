package pages;

import org.openqa.selenium.By;

import base.BasePage;

public class LoginPage extends BasePage {

    private By userEmailField = By.id("email");
    private By userPasswordField = By.id("password");
    private By loginSubmitButton = By.cssSelector("button[type='submit']");
    private By loginErrorMessage = By.xpath("//div[contains(text(),'Invalid email or password')]");

    public void setUserEmail(String userEmail) {
        set(userEmailField, userEmail);
    }

    public void setPassword(String password) {
        set(userPasswordField, password);
    }

    public DashBoardPage clickLoginButton() {
        click(loginSubmitButton);
        return new DashBoardPage();
    }

    public DashBoardPage logIntoApplication(String username, String password) {
        setUserEmail(username);
        setPassword(password);
        return clickLoginButton();
    }

    public String getLoginErrorMessage() {
        return find(loginErrorMessage).getText();
    }
}

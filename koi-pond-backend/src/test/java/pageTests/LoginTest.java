package pageTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import base.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginErrorMessage() {
        loginPage.setUserEmail("admin");
        loginPage.setPassword("admin");
        loginPage.clickLoginButton();

        String actualErrorMessage = loginPage.getLoginErrorMessage();
        String expectedErrorMessage = "Invalid email or password";

        assertEquals(actualErrorMessage, expectedErrorMessage);
    }

}

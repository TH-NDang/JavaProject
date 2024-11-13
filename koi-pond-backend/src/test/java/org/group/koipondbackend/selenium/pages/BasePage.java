package org.group.koipondbackend.selenium.pages;

import org.group.koipondbackend.selenium.utils.TestHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected TestHelper helper;
    private boolean slowMode = true; // Toggle for demo mode

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.helper = new TestHelper(driver);
    }

    public void setSlowMode(boolean slowMode) {
        this.slowMode = slowMode;
    }

    protected void waitAndType(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        if (slowMode) {
            helper.scrollToElement(element);
            helper.highlightElement(element);
            // Type text slowly
            for (char c : text.toCharArray()) {
                element.sendKeys(String.valueOf(c));
                helper.sleep(100); // 100ms delay between each character
            }
            helper.sleep(500);
        } else {
            element.sendKeys(text);
        }
    }

    protected void waitAndClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        if (slowMode) {
            helper.scrollToElement(element);
            helper.highlightElement(element);
            helper.sleep(500);
        }
        element.click();
        if (slowMode)
            helper.sleep(1000);
    }

    protected String waitAndGetText(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        if (slowMode) {
            helper.scrollToElement(element);
            helper.highlightElement(element);
            helper.sleep(500);
        }
        return element.getText();
    }

    protected boolean isElementDisplayed(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (slowMode) {
                helper.scrollToElement(element);
                helper.highlightElement(element);
                helper.sleep(500);
            }
            return element.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    protected boolean areElementsDisplayed(By locator) {
        try {
            List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            return !elements.isEmpty();
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void selectByValue(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        if (slowMode) {
            helper.scrollToElement(element);
            helper.highlightElement(element);
        }
        Select select = new Select(element);
        select.selectByValue(value);
        if (slowMode)
            helper.sleep(1000);
    }
}

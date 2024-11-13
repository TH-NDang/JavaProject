package org.group.koipondbackend.selenium.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class TestHelper {
    private WebDriver driver;
    private static final long DEFAULT_DELAY = 1000; // 1 second delay

    public TestHelper(WebDriver driver) {
        this.driver = driver;
    }

    // Highlight element before action
    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Store original style
        String originalStyle = element.getAttribute("style");
        // Add red border
        js.executeScript(
                "arguments[0].setAttribute('style', 'border: 2px solid red; background: yellow;');",
                element);
        // Small pause to show highlight
        sleep(500);
        // Restore original style
        js.executeScript(
                "arguments[0].setAttribute('style', arguments[1]);",
                element,
                originalStyle);
    }

    // Scroll element into view with smooth animation
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        sleep(500);
    }

    // Controlled sleep
    public void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

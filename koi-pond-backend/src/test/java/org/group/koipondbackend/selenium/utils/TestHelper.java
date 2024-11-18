package org.group.koipondbackend.selenium.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestHelper {
    private WebDriver driver;
    private static final long DEFAULT_DELAY = 1000; // 1 second delay

    public TestHelper(WebDriver driver) {
        this.driver = driver;
    }

    // Highlight element before action
    public void highlightElement(WebElement element) {
        if (element == null)
            return;

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String originalStyle = element.getAttribute("style");

        try {
            js.executeScript(
                    "arguments[0].setAttribute('style', 'border: 2px solid red; background: yellow; transition: all 0.3s ease-in-out;');",
                    element);
            sleep(800);

            js.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element,
                    originalStyle);
        } catch (Exception e) {
            System.out.println("Could not highlight element: " + e.getMessage());
        }
    }

    // Add annotation overlay
    public void addAnnotation(String text) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = """
                    var annotation = document.createElement('div');
                    annotation.style.position = 'fixed';
                    annotation.style.top = '20px';
                    annotation.style.left = '50%';
                    annotation.style.transform = 'translateX(-50%)';
                    annotation.style.backgroundColor = 'rgba(0, 0, 0, 0.8)';
                    annotation.style.color = 'white';
                    annotation.style.padding = '10px 20px';
                    annotation.style.borderRadius = '5px';
                    annotation.style.zIndex = '10000';
                    annotation.style.fontSize = '16px';
                    annotation.style.maxWidth = '80%';
                    annotation.style.textAlign = 'center';
                    annotation.style.boxShadow = '0 2px 10px rgba(0,0,0,0.3)';
                    annotation.style.animation = 'fadeIn 0.5s ease-out';
                    annotation.textContent = arguments[0];
                    document.body.appendChild(annotation);

                    setTimeout(function() {
                        annotation.style.animation = 'fadeOut 0.5s ease-in';
                        setTimeout(function() {
                            annotation.remove();
                        }, 500);
                    }, 2500);

                    if (!document.querySelector('#annotation-styles')) {
                        var styles = document.createElement('style');
                        styles.id = 'annotation-styles';
                        styles.textContent = `
                            @keyframes fadeIn {
                                from { opacity: 0; transform: translate(-50%, -20px); }
                                to { opacity: 1; transform: translate(-50%, 0); }
                            }
                            @keyframes fadeOut {
                                from { opacity: 1; transform: translate(-50%, 0); }
                                to { opacity: 0; transform: translate(-50%, 20px); }
                            }
                        `;
                        document.head.appendChild(styles);
                    }
                """;
        js.executeScript(script, text);
        sleep(3000);
    }

    // Highlight section with label
    public void highlightSection(WebElement element, String label) {
        if (element == null)
            return;

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String originalStyle = element.getAttribute("style");

        // Add highlight and label
        String script = """
                    arguments[0].setAttribute('style', 'border: 2px solid blue; background: rgba(0, 0, 255, 0.1); transition: all 0.3s ease-in-out;');
                    var label = document.createElement('div');
                    label.style.position = 'absolute';
                    label.style.backgroundColor = 'blue';
                    label.style.color = 'white';
                    label.style.padding = '5px 10px';
                    label.style.borderRadius = '3px';
                    label.style.fontSize = '14px';
                    label.style.zIndex = '10000';
                    label.style.boxShadow = '0 2px 5px rgba(0,0,0,0.2)';
                    label.textContent = arguments[1];
                    var rect = arguments[0].getBoundingClientRect();
                    label.style.top = (rect.top + window.scrollY - 25) + 'px';
                    label.style.left = rect.left + 'px';
                    document.body.appendChild(label);
                    return label;
                """;

        Object labelElement = js.executeScript(script, element, label);
        scrollToElement(element);
        sleep(2500);

        // Restore original style and remove label
        js.executeScript(
                "arguments[0].setAttribute('style', arguments[1]); arguments[2].remove();",
                element,
                originalStyle,
                labelElement);
    }

    // Scroll element into view with smooth animation
    public void scrollToElement(WebElement element) {
        if (element == null)
            return;

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                element);
        sleep(800);
    }

    // Smooth scroll through page
    public void smoothScrollPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = """
                    function smoothScroll() {
                        const height = document.documentElement.scrollHeight - window.innerHeight;
                        let position = 0;
                        const scroll = setInterval(() => {
                            window.scrollTo({ top: position, behavior: 'smooth' });
                            position += 5;
                            if (position >= height) clearInterval(scroll);
                        }, 20);
                    }
                    smoothScroll();
                """;
        js.executeScript(script);
        // Wait for scroll to complete
        sleep(Math.min(5000, driver.manage().window().getSize().getHeight() * 15));
    }

    // Type text with visual feedback
    public void typeWithHighlight(WebElement element, String text, String description) {
        if (element == null)
            return;

        scrollToElement(element);
        highlightElement(element);

        if (description != null && !description.isEmpty()) {
            addAnnotation(description);
        }

        // Clear existing text
        element.clear();

        // Type text character by character
        for (char c : text.toCharArray()) {
            element.sendKeys(String.valueOf(c));
            sleep(100);
        }

        sleep(500);
    }

    // Click with visual feedback
    public void clickWithHighlight(WebElement element, String description) {
        if (element == null)
            return;

        scrollToElement(element);
        highlightElement(element);

        if (description != null && !description.isEmpty()) {
            addAnnotation(description);
        }

        element.click();
        sleep(1000);
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

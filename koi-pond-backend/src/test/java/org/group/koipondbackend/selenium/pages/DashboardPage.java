package org.group.koipondbackend.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePage {
    private final By dashboardTitle = By.xpath("//h1[contains(text(),'Tổng quan')]");
    private final By statsCards = By.cssSelector("[class*='stats-card']");

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isDashboardVisible() {
        return isElementDisplayed(dashboardTitle);
    }

    public String getDashboardTitle() {
        return waitAndGetText(dashboardTitle);
    }

    public boolean areStatsCardsVisible() {
        return areElementsDisplayed(statsCards);
    }
}

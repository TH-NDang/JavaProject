package pages;

import org.openqa.selenium.By;

import base.BasePage;

public class DashBoardPage extends BasePage {
    private By dashboardHeader = By.xpath("//h1[contains(text(),'Dashboard')]");

    public boolean isDashboardDisplayed() {
        return find(dashboardHeader).isDisplayed();
    }

}

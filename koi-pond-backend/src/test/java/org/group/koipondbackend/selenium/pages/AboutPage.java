package org.group.koipondbackend.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AboutPage extends BasePage {
    private final By heroSection = By.xpath("//section[contains(@class, 'bg-primary-600')]");
    private final By storySection = By.xpath("//section[.//h2[contains(text(),'Câu chuyện của chúng tôi')]]");
    private final By statsSection = By.xpath("//section[.//h2[contains(text(),'Chỉ số thống kê')]]");
    private final By teamSection = By.xpath("//section[.//h2[contains(text(),'Đội ngũ chuyên gia')]]");
    private final By valuesSection = By.xpath("//section[.//h2[contains(text(),'Giá trị cốt lõi')]]");
    private final By certificationsSection = By.xpath("//section[.//h2[contains(text(),'Chứng nhận')]]");

    public AboutPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void visitAboutPage() {
        driver.get("http://localhost:3000/about");
        helper.addAnnotation("Trang Giới thiệu - Câu chuyện và con người của Koi Pond Co.");
        helper.sleep(1500);
    }

    public void demonstrateHero(String description) {
        WebElement hero = driver.findElement(heroSection);
        helper.highlightSection(hero, description);
        helper.sleep(1500);
    }

    public void demonstrateStory(String description) {
        WebElement story = driver.findElement(storySection);
        helper.highlightSection(story, description);
        helper.smoothScrollPage();
        helper.sleep(2000);
    }

    public void demonstrateStats(String description) {
        WebElement stats = driver.findElement(statsSection);
        helper.highlightSection(stats, description);
        helper.sleep(2000);
    }

    public void demonstrateTeam(String description) {
        WebElement team = driver.findElement(teamSection);
        helper.highlightSection(team, description);
        helper.sleep(2000);
    }

    public void demonstrateValues(String description) {
        WebElement values = driver.findElement(valuesSection);
        helper.highlightSection(values, description);
        helper.sleep(2000);
    }

    public void demonstrateCertifications(String description) {
        WebElement certifications = driver.findElement(certificationsSection);
        helper.highlightSection(certifications, description);
        helper.sleep(1500);
    }
}

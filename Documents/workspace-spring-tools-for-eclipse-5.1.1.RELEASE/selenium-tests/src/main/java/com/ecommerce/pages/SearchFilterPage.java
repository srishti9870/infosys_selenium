package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class SearchFilterPage {
    WebDriver driver;
    WebDriverWait wait;

    By searchInput = By.cssSelector("input[placeholder*='Search']");
    By productCards = By.xpath("//a[contains(@href,'/product/')]");
    By productNames = By.xpath("//h4");
    By noResultsMsg = By.xpath("//h3[contains(text(),'No')]");

    public SearchFilterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void search(String keyword) {
        WebElement search = driver.findElement(searchInput);
        search.clear();
        search.sendKeys(keyword);
        search.submit();
    }

    public int getResultCount() {
        try {
            Thread.sleep(1500);
            return driver.findElements(productCards).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean hasResults() {
        return getResultCount() > 0;
    }

    public boolean isNoResultsMessage() {
        try {
            return driver.findElement(noResultsMsg).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getFirstResultName() {
        List<WebElement> names = driver.findElements(productNames);
        return names.isEmpty() ? "" : names.get(0).getText();
    }

    public boolean verifySearchResults(String keyword) {
        List<WebElement> names = driver.findElements(productNames);
        System.out.println("Searching for: '" + keyword + "' in " + names.size() + " results");
        for (WebElement name : names) {
            String text = name.getText();
            System.out.println("  → " + text);
            if (text.toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public List<String> getAllResultNames() {
        return driver.findElements(productNames)
            .stream().map(WebElement::getText).toList();
    }
}
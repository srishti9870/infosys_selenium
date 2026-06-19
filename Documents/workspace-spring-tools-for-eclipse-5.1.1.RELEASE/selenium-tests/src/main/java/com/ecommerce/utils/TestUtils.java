package com.ecommerce.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TestUtils {
    
    // Explicit Wait for element
    public static WebElement waitForElement(WebDriver driver, By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    // Click using JavaScript
    public static void clickJS(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
    
    // Scroll to element
    public static void scrollTo(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    // Get text safely
    public static String getTextSafe(WebDriver driver, By locator) {
        try {
            return driver.findElement(locator).getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    // Wait for page load
    public static void waitForPageLoad(WebDriver driver) {
        try { Thread.sleep(2000); } catch (Exception e) {}
    }
    
    // Check if element exists
    public static boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
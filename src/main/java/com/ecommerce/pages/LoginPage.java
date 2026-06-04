package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;

    // ===== LOCATORS (ID, XPath, CSS) =====

    // By XPath
    By usernameField = By.xpath("//input[@placeholder='Enter username']");
    By passwordField = By.xpath("//input[@placeholder='Enter password']");
    By signInButton = By.xpath("//button[contains(text(),'Sign')]");
    By errorMsg = By.xpath("//div[contains(@style,'#dc2626')]");
    By registerLink = By.xpath("//a[contains(text(),'Create')]");

    // By CSS Selector
    By formCSS = By.cssSelector("form");
    By inputsCSS = By.cssSelector("input");

    // By ID (if available)
    // By usernameId = By.id("username");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignIn() {
        driver.findElement(signInButton).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSignIn();
    }

    public String getErrorMessage() {
        try {
            return driver.findElement(errorMsg).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isFormDisplayed() {
        return driver.findElement(formCSS).isDisplayed();
    }
}
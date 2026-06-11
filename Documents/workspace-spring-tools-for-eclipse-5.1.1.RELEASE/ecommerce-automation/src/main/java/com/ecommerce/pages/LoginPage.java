package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    // ========== LOCATORS ==========

    // By ID
    // (Login page inputs don't have IDs, using placeholders)

    // By XPath
    By usernameField = By.xpath("//input[@placeholder='Username' or @placeholder='Enter username']");
    By passwordField = By.xpath("//input[@placeholder='Password' or @placeholder='Enter password']");
    By signInButton = By.xpath("//button[contains(text(),'Sign')]");
    By registerLink = By.xpath("//a[contains(text(),'Create') or contains(text(),'Register')]");
    By errorMessage = By.xpath("//div[contains(@style,'red') or contains(@style,'error')]");
    By brandText = By.xpath("//h2[contains(text(),'e-shop')] | //div[contains(text(),'e-shop')]");

    // By CSS Selector
    By loginFormCSS = By.cssSelector("form");
    By inputFieldsCSS = By.cssSelector("input[type='text'], input[type='password']");

    // ========== CONSTRUCTOR ==========
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ========== ACTIONS ==========

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
            return driver.findElement(errorMessage).getText();
        } catch (Exception e) {
            return "No error message";
        }
    }

    public boolean isLoginPageDisplayed() {
        return driver.findElement(signInButton).isDisplayed();
    }

    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    public boolean isBrandDisplayed() {
        try {
            return driver.findElement(brandText).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
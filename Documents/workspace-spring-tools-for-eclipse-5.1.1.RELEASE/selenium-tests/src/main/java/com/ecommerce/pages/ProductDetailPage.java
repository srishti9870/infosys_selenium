package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductDetailPage {
    WebDriver driver;
    WebDriverWait wait;

    By productName = By.xpath("//h1");
    By productPrice = By.xpath("//span[contains(text(),'₹')]");
    By productCategory = By.xpath("//span[contains(@style,'uppercase')]");
    By addToCartBtn = By.xpath("//button[contains(text(),'Add')]");
    By addedConfirmation = By.xpath("//button[contains(text(),'Added')]");
    By quantityPlus = By.xpath("//button[contains(text(),'+')]");
    By stockStatus = By.xpath("//span[contains(text(),'Stock') or contains(text(),'stock')]");
    By breadcrumbHome = By.xpath("//a[contains(@href,'/home')]");

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getProductName() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(productName));
            return driver.findElement(productName).getText();
        } catch (Exception e) { return ""; }
    }

    public String getProductPrice() {
        try { return driver.findElement(productPrice).getText(); } 
        catch (Exception e) { return ""; }
    }

    public String getCategory() {
        try { return driver.findElement(productCategory).getText(); } 
        catch (Exception e) { return ""; }
    }

    public boolean isAddToCartVisible() {
        try { return driver.findElement(addToCartBtn).isDisplayed(); } 
        catch (Exception e) { return false; }
    }

    public boolean isStockStatusVisible() {
        try { return driver.findElement(stockStatus).isDisplayed(); } 
        catch (Exception e) { return false; }
    }

    public void clickAddToCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
            Thread.sleep(1000);
        } catch (Exception e) {}
    }

    public boolean isAddedToCart() {
        try { return driver.findElement(addedConfirmation).isDisplayed(); } 
        catch (Exception e) { return false; }
    }

    public void increaseQuantity(int times) {
        for (int i = 0; i < times; i++) {
            try { driver.findElement(quantityPlus).click(); Thread.sleep(300); } 
            catch (Exception e) {}
        }
    }

    public void clickBreadcrumbHome() {
        try { driver.findElement(breadcrumbHome).click(); } 
        catch (Exception e) {}
    }
}
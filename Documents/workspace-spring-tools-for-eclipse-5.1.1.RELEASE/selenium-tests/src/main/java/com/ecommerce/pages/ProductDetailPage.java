package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductDetailPage {
    WebDriver driver;
    WebDriverWait wait;

    By productName = By.xpath("//h1");
    By productPrice = By.xpath("//span[contains(text(),'₹')]");
    By productCategory = By.xpath("//span[contains(@style,'uppercase')]");
    By productDescription = By.xpath("//p[contains(@style,'14px')]");
    By addToCartBtn = By.xpath("//button[contains(text(),'Add')]");
    By stockStatus = By.xpath("//span[contains(text(),'Stock') or contains(text(),'stock')]");
    By breadcrumb = By.xpath("//a[contains(@href,'/home')]");

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getProductName() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(productName));
            return driver.findElement(productName).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getProductPrice() {
        try {
            return driver.findElement(productPrice).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getCategory() {
        try {
            return driver.findElement(productCategory).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isAddToCartVisible() {
        try {
            return driver.findElement(addToCartBtn).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStockStatusVisible() {
        try {
            return driver.findElement(stockStatus).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickBreadcrumbHome() {
        driver.findElement(breadcrumb).click();
    }

    public void addToCart() {
        driver.findElement(addToCartBtn).click();
    }
}
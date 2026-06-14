package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    By cartItems = By.xpath("//h4");
    By checkoutBtn = By.xpath("//button[contains(text(),'Checkout') or contains(text(),'Proceed')]");
    By emptyMsg = By.xpath("//h3[contains(text(),'empty')]");
    By shopNowBtn = By.xpath("//a[contains(text(),'Shop')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isCartPage() {
        return driver.getCurrentUrl().contains("/cart");
    }

    public boolean isCartEmpty() {
        try {
            return driver.findElement(emptyMsg).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getItemCount() {
        return driver.findElements(cartItems).size();
    }

    public void clickCheckout() {
        driver.findElement(checkoutBtn).click();
    }

    public void clickShopNow() {
        driver.findElement(shopNowBtn).click();
    }
}
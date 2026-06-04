package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    WebDriver driver;

    // ===== LOCATORS =====

    // By XPath
    By navbarXPath = By.xpath("//nav");
    By searchBarXPath = By.xpath("//input[@placeholder*='Search']");
    By productCardsXPath = By.xpath("//a[contains(@href,'/product/')]");
    By cartLink = By.xpath("//a[contains(text(),'Cart')]");
    By ordersLink = By.xpath("//a[contains(text(),'Orders')]");
    By wishlistLink = By.xpath("//a[contains(text(),'Wishlist')]");
    By userAvatar = By.xpath("//div[contains(@style,'border-radius: 50%')]");
    By logoutBtn = By.xpath("//span[contains(text(),'Logout')]");
    By signInBtn = By.xpath("//a[contains(text(),'Sign In')]");

    // By CSS
    By heroCSS = By.cssSelector("div[style*='100vh']");
    By categoryImagesCSS = By.cssSelector("img[alt]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isNavbarDisplayed() {
        return driver.findElement(navbarXPath).isDisplayed();
    }

    public void searchProduct(String keyword) {
        WebElement search = driver.findElement(searchBarXPath);
        search.clear();
        search.sendKeys(keyword);
        search.submit();
    }

    public int getProductCount() {
        return driver.findElements(productCardsXPath).size();
    }

    public void clickFirstProduct() {
        List<WebElement> products = driver.findElements(productCardsXPath);
        if (!products.isEmpty()) products.get(0).click();
    }

    public void clickCart() { driver.findElement(cartLink).click(); }
    public void clickOrders() { driver.findElement(ordersLink).click(); }
    public void clickWishlist() { driver.findElement(wishlistLink).click(); }

    public void logout() {
        driver.findElement(userAvatar).click();
        try { Thread.sleep(500); } catch (Exception e) {}
        driver.findElement(logoutBtn).click();
    }

    public boolean isLoggedIn() {
        try {
            return driver.findElement(userAvatar).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
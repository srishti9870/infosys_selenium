package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HomePage {
    WebDriver driver;

    By productCards = By.xpath("//a[contains(@href,'/product/')]");
    By searchBar = By.cssSelector("input[placeholder*='Search']");
    By cartLink = By.xpath("//a[contains(@href,'/cart') or contains(text(),'Cart')]");
    By ordersLink = By.xpath("//a[contains(text(),'Orders')]");
    By wishlistLink = By.xpath("//a[contains(text(),'Wishlist')]");
    By userAvatar = By.xpath("//div[contains(@style,'50%')]");
    By logoutBtn = By.xpath("//span[contains(text(),'Logout')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isHomePage() {
        return driver.getCurrentUrl().contains("/home");
    }

    public int getProductCount() {
        return driver.findElements(productCards).size();
    }

    public void clickFirstProduct() {
        List<WebElement> products = driver.findElements(productCards);
        if (!products.isEmpty()) {
            WebElement first = products.get(0);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", first);
            try { Thread.sleep(500); } catch (Exception e) {}
            first.click();
        }
    }

    public void searchProduct(String keyword) {
        WebElement search = driver.findElement(searchBar);
        search.clear();
        try { Thread.sleep(300); } catch (Exception e) {}
        search.sendKeys(keyword);
        try { Thread.sleep(300); } catch (Exception e) {}
        search.submit();
    }

    public void clickCart() {
        driver.findElement(cartLink).click();
    }

    public void clickOrders() {
        driver.findElement(ordersLink).click();
    }

    public void clickWishlist() {
        driver.findElement(wishlistLink).click();
    }

    public void logout() {
        driver.findElement(userAvatar).click();
        try { Thread.sleep(500); } catch (Exception e) {}
        driver.findElement(logoutBtn).click();
    }
}
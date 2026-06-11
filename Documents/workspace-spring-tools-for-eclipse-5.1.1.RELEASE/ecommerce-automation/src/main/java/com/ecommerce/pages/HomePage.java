package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HomePage {

    WebDriver driver;

    // ========== LOCATORS ==========

    // By CSS
    By navbarCSS = By.cssSelector("nav");
    By logoCSS = By.cssSelector("nav a[href='/home']");
    By searchBarCSS = By.cssSelector("input[placeholder*='Search']");
    By productCardsCSS = By.cssSelector("a[href*='/product/']");
    By categoryCardsCSS = By.cssSelector("img[alt]");

    // By XPath
    By heroSection = By.xpath("//div[contains(@style,'100vh')]");
    By carouselDots = By.xpath("//button[contains(@style,'border-radius: 5px')]");
    By shopNowBtn = By.xpath("//a[contains(text(),'Shop')]");
    By featuredProducts = By.xpath("//h2[contains(text(),'Trending')]");
    By wishlistLink = By.xpath("//a[contains(text(),'Wishlist')]");
    By ordersLink = By.xpath("//a[contains(text(),'Orders')]");
    By cartLink = By.xpath("//a[contains(text(),'Cart')]");
    By userAvatar = By.xpath("//div[contains(@style,'border-radius: 50%') and contains(@style,'background')]");
    By logoutButton = By.xpath("//span[contains(text(),'Logout')]");
    By signInButton = By.xpath("//a[contains(text(),'Sign In')]");
    By themeToggle = By.xpath("//button[contains(@style,'font-size: 20px')]");

    // By ID - Generally not available, using data attributes if present

    // ========== CONSTRUCTOR ==========
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // ========== ACTIONS ==========

    public boolean isHomePageDisplayed() {
        try {
            return driver.findElement(heroSection).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNavbarDisplayed() {
        return driver.findElement(navbarCSS).isDisplayed();
    }

    public boolean isSearchBarDisplayed() {
        return driver.findElement(searchBarCSS).isDisplayed();
    }

    public void searchProduct(String keyword) {
        WebElement search = driver.findElement(searchBarCSS);
        search.clear();
        search.sendKeys(keyword);
        search.submit();
    }

    public int getProductCount() {
        return driver.findElements(productCardsCSS).size();
    }

    public void clickFirstProduct() {
        List<WebElement> products = driver.findElements(productCardsCSS);
        if (!products.isEmpty()) {
            products.get(0).click();
        }
    }

    public void clickWishlist() {
        driver.findElement(wishlistLink).click();
    }

    public void clickCart() {
        driver.findElement(cartLink).click();
    }

    public void clickOrders() {
        driver.findElement(ordersLink).click();
    }

    public void clickShopNow() {
        driver.findElement(shopNowBtn).click();
    }

    public void clickThemeToggle() {
        driver.findElement(themeToggle).click();
    }

    public void logout() {
        driver.findElement(userAvatar).click();
        try { Thread.sleep(500); } catch (Exception e) {}
        driver.findElement(logoutButton).click();
    }

    public boolean isUserLoggedIn() {
        try {
            return driver.findElement(userAvatar).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSignInButtonDisplayed() {
        try {
            return driver.findElement(signInButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getCategoryCount() {
        return driver.findElements(categoryCardsCSS).size();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
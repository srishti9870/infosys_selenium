package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductPage {
    WebDriver driver;

    By productCards = By.xpath("//a[contains(@href,'/product/')]");
    By productNames = By.xpath("//h4");
    By productPrices = By.xpath("//span[contains(text(),'₹')]");
    By productImages = By.xpath("//img");
    By noProductsMsg = By.xpath("//h3[contains(text(),'No products')]");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get("http://localhost:3000/products");
    }

    public int getProductCount() {
        return driver.findElements(productCards).size();
    }

    public boolean areProductsVisible() {
        return getProductCount() > 0;
    }

    public boolean areProductImagesVisible() {
        List<WebElement> images = driver.findElements(productImages);
        for (WebElement img : images) {
            if (!img.isDisplayed()) return false;
        }
        return true;
    }

    public boolean areProductNamesVisible() {
        List<WebElement> names = driver.findElements(productNames);
        for (WebElement name : names) {
            if (!name.isDisplayed() || name.getText().isEmpty()) return false;
        }
        return true;
    }

    public boolean areProductPricesVisible() {
        List<WebElement> prices = driver.findElements(productPrices);
        for (WebElement price : prices) {
            if (!price.isDisplayed()) return false;
        }
        return true;
    }

    public String getFirstProductName() {
        List<WebElement> names = driver.findElements(productNames);
        return names.isEmpty() ? "" : names.get(0).getText();
    }

    public void clickFirstProduct() {
        List<WebElement> products = driver.findElements(productCards);
        if (!products.isEmpty()) {
            products.get(0).click();
        }
    }
}
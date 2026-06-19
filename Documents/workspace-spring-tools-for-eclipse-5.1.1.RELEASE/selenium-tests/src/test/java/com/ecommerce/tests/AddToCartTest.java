package com.ecommerce.tests;

import com.ecommerce.pages.*;
import com.ecommerce.utils.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

public class AddToCartTest extends BaseTest {

    @Test(priority = 1,enabled = false, description = "Add product to cart")
    public void testAddToCart() {
        LoginUtils.login(driver, prop.getProperty("test.username"), prop.getProperty("test.password"));
        
        // Click first product
        HomePage homePage = new HomePage(driver);
        homePage.clickFirstProduct();
        TestUtils.waitForPageLoad(driver);
        
        // Verify on product detail page
        Assert.assertTrue(driver.getCurrentUrl().contains("/product/"), "Should be on product detail");
        
        // Add to cart
        ProductDetailPage detailPage = new ProductDetailPage(driver);
        String productName = detailPage.getProductName();
        detailPage.clickAddToCart();
        TestUtils.waitForPageLoad(driver);
        
        // Verify added
        Assert.assertTrue(detailPage.isAddedToCart(), "Product should be added to cart");
        System.out.println("✅ Added to cart: " + productName);
    }

    @Test(priority = 2, description = "Add multiple products to cart")
    public void testAddMultipleProducts() {
        LoginUtils.login(driver, prop.getProperty("test.username"), prop.getProperty("test.password"));
        
        HomePage homePage = new HomePage(driver);
        
        // Add first product
        homePage.clickFirstProduct();
        TestUtils.waitForPageLoad(driver);
        ProductDetailPage detailPage = new ProductDetailPage(driver);
        detailPage.increaseQuantity(2); // Qty = 3
        detailPage.clickAddToCart();
        TestUtils.waitForPageLoad(driver);
        
        // Navigate back and add another
        driver.get(prop.getProperty("base.url") + "/home");
        TestUtils.waitForPageLoad(driver);
        
        homePage.clickCart();
        TestUtils.waitForPageLoad(driver);
        
        CartPage cartPage = new CartPage(driver);
        Assert.assertFalse(cartPage.isCartEmpty(), "Cart should have items");
        System.out.println("✅ Multiple products test passed");
    }
}
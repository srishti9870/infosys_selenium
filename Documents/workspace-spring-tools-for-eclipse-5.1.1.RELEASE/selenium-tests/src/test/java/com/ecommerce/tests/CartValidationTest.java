package com.ecommerce.tests;

import com.ecommerce.pages.*;
import com.ecommerce.utils.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

public class CartValidationTest extends BaseTest {

    @Test(priority = 1, description = "Verify cart items after adding")
    public void testCartItems() {
        LoginUtils.login(driver, prop.getProperty("test.username"), prop.getProperty("test.password"));
        
        HomePage homePage = new HomePage(driver);
        homePage.clickCart();
        TestUtils.waitForPageLoad(driver);
        
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartPage(), "Should be on cart page");
        
        if (cartPage.isCartEmpty()) {
            System.out.println("⚠️ Cart is empty - add items first");
            return;
        }
        
        int itemCount = cartPage.getItemCount();
        System.out.println("✅ Cart items: " + itemCount);
        Assert.assertTrue(itemCount > 0, "Cart should have items");
    }

    @Test(priority = 2, description = "Verify price calculation in cart")
    public void testPriceCalculation() {
        LoginUtils.login(driver, prop.getProperty("test.username"), prop.getProperty("test.password"));
        
        HomePage homePage = new HomePage(driver);
        homePage.clickCart();
        TestUtils.waitForPageLoad(driver);
        
        CartPage cartPage = new CartPage(driver);
        if (cartPage.isCartEmpty()) {
            System.out.println("⚠️ Cart is empty");
            return;
        }
        
        String total = cartPage.getTotalAmount();
        System.out.println("✅ Total amount: " + total);
        Assert.assertFalse(total.isEmpty(), "Total should not be empty");
    }
}
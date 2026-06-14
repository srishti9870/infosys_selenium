package com.ecommerce.tests;

import com.ecommerce.pages.*;
import com.ecommerce.utils.LoginUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductListingTest extends BaseTest {

    @Test(priority = 1, description = "Verify product listing page")
    public void testProductListingPage() {
        LoginUtils.login(driver, "srishti_mamgai", "mamgai@345678");

        ProductPage productPage = new ProductPage(driver);
        productPage.openPage();
        try { Thread.sleep(2000); } catch (Exception e) {}

        // Validate products are visible
        Assert.assertTrue(productPage.areProductsVisible(), "Products should be visible");
        System.out.println("✅ Product count: " + productPage.getProductCount());
    }

    @Test(priority = 2, description = "Verify product details visible")
    public void testProductDetailsVisible() {
        LoginUtils.login(driver, "srishti_mamgai", "mamgai@345678");

        ProductPage productPage = new ProductPage(driver);
        productPage.openPage();
        try { Thread.sleep(2000); } catch (Exception e) {}

        // Validate product names
        Assert.assertTrue(productPage.areProductNamesVisible(), "Product names should be visible");
        System.out.println("✅ First product: " + productPage.getFirstProductName());

        // Validate product prices
        Assert.assertTrue(productPage.areProductPricesVisible(), "Product prices should be visible");
        System.out.println("✅ Product prices visible");

        // Validate product images
        Assert.assertTrue(productPage.areProductImagesVisible(), "Product images should be visible");
        System.out.println("✅ Product images visible");
    }

    @Test(priority = 3, description = "Verify product click navigation")
    public void testProductClickNavigation() {
        LoginUtils.login(driver, "srishti_mamgai", "mamgai@345678");

        ProductPage productPage = new ProductPage(driver);
        productPage.openPage();
        try { Thread.sleep(2000); } catch (Exception e) {}

        String productName = productPage.getFirstProductName();
        productPage.clickFirstProduct();
        try { Thread.sleep(2000); } catch (Exception e) {}

        Assert.assertTrue(
            driver.getCurrentUrl().contains("/product/"),
            "Should navigate to product detail"
        );
        System.out.println("✅ Clicked: " + productName + " → Product Detail Page");
    }
}
package com.ecommerce.tests;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.HomePage;
import com.ecommerce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test(priority = 1, description = "Verify product listing")
    public void testProductListing() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            prop.getProperty("test.username"),
            prop.getProperty("test.password")
        );

        try { Thread.sleep(2000); } catch (Exception e) {}

        HomePage homePage = new HomePage(driver);
        int count = homePage.getProductCount();
        Assert.assertTrue(count > 0, "Products should be visible");
        System.out.println("✅ Product count: " + count);
    }

    @Test(priority = 2, description = "Verify search")
    public void testSearch() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            prop.getProperty("test.username"),
            prop.getProperty("test.password")
        );

        try { Thread.sleep(2000); } catch (Exception e) {}

        HomePage homePage = new HomePage(driver);
        homePage.searchProduct("iPhone");

        try { Thread.sleep(1000); } catch (Exception e) {}

        Assert.assertTrue(
            driver.getCurrentUrl().contains("search"),
            "URL should contain search"
        );
        System.out.println("✅ Search Test Passed");
    }

    @Test(priority = 3, description = "Verify product detail navigation")
    public void testProductDetailNavigation() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            prop.getProperty("test.username"),
            prop.getProperty("test.password")
        );

        try { Thread.sleep(2000); } catch (Exception e) {}

        HomePage homePage = new HomePage(driver);
        homePage.clickFirstProduct();

        try { Thread.sleep(1000); } catch (Exception e) {}

        Assert.assertTrue(
            driver.getCurrentUrl().contains("/product/"),
            "Should be on product detail page"
        );
        System.out.println("✅ Product Detail Navigation Test Passed");
    }
}
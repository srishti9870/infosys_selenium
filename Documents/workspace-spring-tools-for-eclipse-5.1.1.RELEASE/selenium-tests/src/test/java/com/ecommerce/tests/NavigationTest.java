package com.ecommerce.tests;

import com.ecommerce.pages.HomePage;
import com.ecommerce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("srishti_mamgai", "mamgai@345678");
        try { Thread.sleep(3000); } catch (Exception e) {}
    }

    @Test(priority = 1, description = "Verify product listing")
    public void testProductListing() {
        login();
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePage(), "Should be on home page");
        
        int count = homePage.getProductCount();
        Assert.assertTrue(count > 0, "Products should be visible");
        System.out.println("✅ Product count: " + count);
    }

    @Test(priority = 2, description = "Verify product detail navigation")
    public void testProductDetail() {
        login();
        HomePage homePage = new HomePage(driver);
        homePage.clickFirstProduct();

        try { Thread.sleep(2000); } catch (Exception e) {}

        Assert.assertTrue(
            driver.getCurrentUrl().contains("/product/"),
            "Should navigate to product detail"
        );
        System.out.println("✅ Product Detail Navigation Passed");
    }

    @Test(priority = 3, description = "Verify search")
    public void testSearch() {
        login();
        HomePage homePage = new HomePage(driver);
        homePage.searchProduct("iPhone");

        try { Thread.sleep(2000); } catch (Exception e) {}

        Assert.assertTrue(
            driver.getCurrentUrl().contains("search"),
            "URL should contain search"
        );
        System.out.println("✅ Search Test Passed");
    }

    @Test(priority = 4, description = "Verify cart navigation")
    public void testCartNavigation() {
        login();
        HomePage homePage = new HomePage(driver);
        homePage.clickCart();

        try { Thread.sleep(1000); } catch (Exception e) {}

        Assert.assertTrue(
            driver.getCurrentUrl().contains("/cart"),
            "Should navigate to cart"
        );
        System.out.println("✅ Cart Navigation Passed");
    }

    @Test(priority = 5, description = "Verify orders navigation")
    public void testOrdersNavigation() {
        login();
        HomePage homePage = new HomePage(driver);
        homePage.clickOrders();

        try { Thread.sleep(1000); } catch (Exception e) {}

        Assert.assertTrue(
            driver.getCurrentUrl().contains("/orders"),
            "Should navigate to orders"
        );
        System.out.println("✅ Orders Navigation Passed");
    }
}
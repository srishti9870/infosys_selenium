package com.ecommerce.tests;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.HomePage;
import com.ecommerce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Verify valid login")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);

        // Verify login page is displayed
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");

        // Perform login
        String username = prop.getProperty("test.username");
        String password = prop.getProperty("test.password");
        loginPage.login(username, password);

        // Verify successful login
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUserLoggedIn(), "User should be logged in");
        Assert.assertTrue(homePage.isNavbarDisplayed(), "Navbar should be visible");
    }

    @Test(priority = 2, description = "Verify invalid login")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);

        // Try login with wrong password
        loginPage.login("manish", "wrongpassword");

        // Verify error message
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Invalid") || error.contains("invalid"),
            "Error message should be displayed for invalid login");
    }

    @Test(priority = 3, description = "Verify logout functionality")
    public void testLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(prop.getProperty("test.username"), prop.getProperty("test.password"));

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUserLoggedIn(), "Should be logged in");

        // Perform logout
        homePage.logout();

        // Verify redirected to login
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Should be on login page after logout");
    }

    @Test(priority = 4, description = "Verify search functionality")
    public void testSearchProduct() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(prop.getProperty("test.username"), prop.getProperty("test.password"));

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isSearchBarDisplayed(), "Search bar should be visible");

        // Search for a product
        homePage.searchProduct("iPhone");

        // Verify URL contains search parameter
        Assert.assertTrue(homePage.getCurrentUrl().contains("search"),
            "URL should contain search parameter");
    }

    @Test(priority = 5, description = "Verify navigation to product detail")
    public void testNavigateToProductDetail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(prop.getProperty("test.username"), prop.getProperty("test.password"));

        HomePage homePage = new HomePage(driver);
        int productCount = homePage.getProductCount();
        Assert.assertTrue(productCount > 0, "Products should be displayed");

        // Click first product
        homePage.clickFirstProduct();

        // Verify URL changed to product detail
        Assert.assertTrue(homePage.getCurrentUrl().contains("/product/"),
            "Should navigate to product detail page");
    }
}
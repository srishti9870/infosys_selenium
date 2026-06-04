package com.ecommerce.tests;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.HomePage;
import com.ecommerce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Verify valid login redirects to home")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            prop.getProperty("test.username"),
            prop.getProperty("test.password")
        );

        try { Thread.sleep(2000); } catch (Exception e) {}

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isNavbarDisplayed(), "Navbar should display");
        Assert.assertTrue(homePage.isLoggedIn(), "User should be logged in");
        System.out.println("✅ Valid Login Test Passed");
    }

    @Test(priority = 2, description = "Verify invalid login shows error")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("manish", "wrongpassword");

        try { Thread.sleep(1000); } catch (Exception e) {}

        String error = loginPage.getErrorMessage();
        Assert.assertFalse(error.isEmpty(), "Error message should appear");
        System.out.println("✅ Invalid Login Test Passed");
    }

    @Test(priority = 3, description = "Verify logout")
    public void testLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            prop.getProperty("test.username"),
            prop.getProperty("test.password")
        );

        try { Thread.sleep(2000); } catch (Exception e) {}

        HomePage homePage = new HomePage(driver);
        homePage.logout();

        try { Thread.sleep(1000); } catch (Exception e) {}

        Assert.assertTrue(
            driver.getCurrentUrl().contains("/login"),
            "Should redirect to login after logout"
        );
        System.out.println("✅ Logout Test Passed");
    }
}
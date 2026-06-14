package com.ecommerce.tests;

import com.ecommerce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Verify valid login")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("srishti_mamgai", "mamgai@345678");

        try { Thread.sleep(3000); } catch (Exception e) {}

        System.out.println("Current URL: " + driver.getCurrentUrl());
        
        Assert.assertTrue(
            driver.getCurrentUrl().contains("/home"),
            "Login failed - not redirected to home"
        );
        System.out.println("✅ Valid Login Test Passed!");
    }

    @Test(priority = 2, description = "Verify invalid login")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("srishti_mamgai", "wrongpassword");

        try { Thread.sleep(5000); } catch (Exception e) {}

        System.out.println("Current URL: " + driver.getCurrentUrl());
        Assert.assertTrue(
            driver.getCurrentUrl().contains("/login"),
            "Should stay on login page"
        );
        System.out.println("✅ Invalid Login Test Passed!");
    }
}
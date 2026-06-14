package com.ecommerce.tests;

import com.ecommerce.pages.*;
import com.ecommerce.utils.LoginUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutSessionTest extends BaseTest {

    @Test(priority = 1, description = "Verify logout functionality")
    public void testLogout() {
        LoginUtils.login(driver, "srishti_mamgai", "mamgai@345678");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePage(), "Should be on home page");
        System.out.println("✅ Logged in successfully");

        // Check token exists
        Object token = ((JavascriptExecutor) driver)
            .executeScript("return localStorage.getItem('token');");
        Assert.assertNotNull(token, "Token should exist");
        System.out.println("✅ Token exists");

        // Logout
        homePage.logout();
        try { Thread.sleep(3000); } catch (Exception e) {}

        // Check URL
        String url = driver.getCurrentUrl();
        System.out.println("URL after logout: " + url);
        Assert.assertTrue(url.contains("/login"), "Should redirect to login");
        System.out.println("✅ Logout Test Passed");
    }

    @Test(priority = 2, description = "Verify session handling")
    public void testSessionHandling() {
        // Login
        LoginUtils.login(driver, "srishti_mamgai", "mamgai@345678");
        Assert.assertTrue(driver.getCurrentUrl().contains("/home"), "Should be logged in");
        System.out.println("✅ Session active");

        // Check token
        Object token = ((JavascriptExecutor) driver)
            .executeScript("return localStorage.getItem('token');");
        Assert.assertNotNull(token, "Token should exist after login");
        System.out.println("✅ Token stored");

        // Refresh - session persists
        driver.navigate().refresh();
        try { Thread.sleep(2000); } catch (Exception e) {}
        Assert.assertTrue(driver.getCurrentUrl().contains("/home"), "Session persists after refresh");
        System.out.println("✅ Session persists after refresh");

        // Logout
        HomePage homePage = new HomePage(driver);
        homePage.logout();
        try { Thread.sleep(3000); } catch (Exception e) {}

        // Check redirect
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"), "Should redirect to login");
        System.out.println("✅ Redirected to login");

        // Check token removed
        Object tokenAfter = ((JavascriptExecutor) driver)
            .executeScript("return localStorage.getItem('token');");
        Assert.assertNull(tokenAfter, "Token should be removed after logout");
        System.out.println("✅ Token removed from localStorage");

        System.out.println("✅ Session Handling Test Passed");
    }
}
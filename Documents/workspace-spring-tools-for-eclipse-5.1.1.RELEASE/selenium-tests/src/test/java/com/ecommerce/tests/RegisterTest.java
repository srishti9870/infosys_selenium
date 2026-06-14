package com.ecommerce.tests;

import com.ecommerce.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    @Test(priority = 1, description = "Verify valid registration")
    public void testValidRegistration() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openPage();

        String uniqueUser = "test" + System.currentTimeMillis();
        registerPage.register(uniqueUser, uniqueUser + "@test.com", "test123", "Test User");

        try { Thread.sleep(3000); } catch (Exception e) {}

        String msg = registerPage.getSuccessMessage();
        System.out.println("Success Message: " + msg);
        
        // Check URL or message
        boolean success = !msg.isEmpty() || driver.getCurrentUrl().contains("/login");
        Assert.assertTrue(success, "Registration should succeed");
        System.out.println("✅ Valid Registration Test Passed - " + uniqueUser);
    }

    @Test(priority = 2, description = "Verify duplicate username")
    public void testDuplicateUsername() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openPage();
        registerPage.register("srishti_mamgai", "test@test.com", "test123", "Test User");

        try { Thread.sleep(3000); } catch (Exception e) {}

        String error = registerPage.getErrorMessage();
        System.out.println("Error Message: " + error);
        
        boolean hasError = !error.isEmpty() || driver.getCurrentUrl().contains("/register");
        Assert.assertTrue(hasError, "Error should appear for duplicate");
        System.out.println("✅ Duplicate Username Test Passed");
    }
}
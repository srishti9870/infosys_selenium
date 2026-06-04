package tests;

import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("manish", "manish123");
        
        try { Thread.sleep(2000); } catch (Exception e) {}
        
        Assert.assertTrue(
            driver.getCurrentUrl().contains("/home"),
            "Login failed - not redirected to home"
        );
        System.out.println("✅ Login Test Passed!");
    }
}
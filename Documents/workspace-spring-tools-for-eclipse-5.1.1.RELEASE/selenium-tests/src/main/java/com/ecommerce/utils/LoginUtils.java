package com.ecommerce.utils;

import com.ecommerce.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginUtils {
    
    public static void login(WebDriver driver, String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        try { Thread.sleep(3000); } catch (Exception e) {}
    }
}
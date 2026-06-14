package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class RegisterPage {
    WebDriver driver;

    By createAccountBtn = By.xpath("//button[contains(text(),'Create')]");
    By successMsg = By.xpath("//div[contains(@style,'#059669') or contains(@style,'green')]");
    By errorMsg = By.xpath("//div[contains(@style,'#dc2626') or contains(@style,'red')]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get("http://localhost:3000/register");
    }

    public void fillForm(String username, String email, String password, String fullName) {
        // Username - first text input
        driver.findElement(By.xpath("//input[@type='text']")).clear();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(username);

        // Full Name - placeholder="John Doe"
        driver.findElement(By.xpath("//input[@placeholder='John Doe']")).clear();
        driver.findElement(By.xpath("//input[@placeholder='John Doe']")).sendKeys(fullName);

        // Email - type="email"
        driver.findElement(By.xpath("//input[@type='email']")).clear();
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);

        // Password + Confirm Password
        List<WebElement> passwords = driver.findElements(By.xpath("//input[@type='password']"));
        if (passwords.size() >= 2) {
            passwords.get(0).clear();
            passwords.get(0).sendKeys(password);
            passwords.get(1).clear();
            passwords.get(1).sendKeys(password);
        }
    }

    public void checkTerms() {
        try {
            WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        } catch (Exception e) {}
    }

    public void clickCreateAccount() {
        driver.findElement(createAccountBtn).click();
    }

    public void register(String username, String email, String password, String fullName) {
        fillForm(username, email, password, fullName);
        checkTerms();
        try { Thread.sleep(500); } catch (Exception e) {}
        clickCreateAccount();
    }

    public String getSuccessMessage() {
        try {
            Thread.sleep(1000);
            // Try multiple ways
            try { return driver.findElement(successMsg).getText(); } catch (Exception e1) {}
            try { return driver.findElement(By.xpath("//div[contains(text(),'Account') or contains(text(),'success')]")).getText(); } catch (Exception e2) {}
            try { return driver.findElement(By.xpath("//div[contains(text(),'created')]")).getText(); } catch (Exception e3) {}
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public String getErrorMessage() {
        try {
            Thread.sleep(1000);
            try { return driver.findElement(errorMsg).getText(); } catch (Exception e1) {}
            try { return driver.findElement(By.xpath("//div[contains(text(),'already') or contains(text(),'taken')]")).getText(); } catch (Exception e2) {}
            try { return driver.findElement(By.xpath("//div[contains(text(),'failed') or contains(text(),'error')]")).getText(); } catch (Exception e3) {}
            return "";
        } catch (Exception e) {
            return "";
        }
    }
}
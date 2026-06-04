package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    By usernameField = By.xpath("//input[@placeholder='Enter username']");
    By passwordField = By.xpath("//input[@placeholder='Enter password']");
    By signInButton = By.xpath("//button[contains(text(),'Sign')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(signInButton).click();
    }
}
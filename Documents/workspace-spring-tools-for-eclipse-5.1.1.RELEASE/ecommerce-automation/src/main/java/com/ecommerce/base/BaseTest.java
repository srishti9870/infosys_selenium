package com.ecommerce.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Properties prop;

    @BeforeMethod
    public void setUp() {
       
        prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Setup browser
        String browser = prop.getProperty("browser", "chrome");

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (prop.getProperty("headless").equals("true")) {
                options.addArguments("--headless");
            }
            options.addArguments("--disable-notifications");
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(
            Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicit.wait", "10")))
        );
        driver.manage().timeouts().pageLoadTimeout(
            Duration.ofSeconds(Integer.parseInt(prop.getProperty("page.load.timeout", "30")))
        );

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to base URL
        driver.get(prop.getProperty("base.url"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
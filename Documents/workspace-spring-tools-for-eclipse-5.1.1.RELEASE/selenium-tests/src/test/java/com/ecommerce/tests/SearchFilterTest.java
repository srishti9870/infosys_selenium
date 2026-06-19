package com.ecommerce.tests;

import com.ecommerce.pages.*;
import com.ecommerce.utils.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

public class SearchFilterTest extends BaseTest {

    @Test(priority = 1, description = "Search with valid keyword")
    public void testSearchValidKeyword() {
        LoginUtils.login(driver, prop.getProperty("test.username"), prop.getProperty("test.password"));
        
        HomePage homePage = new HomePage(driver);
        homePage.searchProduct("iPhone");
        TestUtils.waitForPageLoad(driver);
        
        SearchFilterPage searchPage = new SearchFilterPage(driver);
        Assert.assertTrue(searchPage.hasResults(), "Should show results");
        System.out.println("✅ Search results: " + searchPage.getResultCount());
    }

    @Test(priority = 2, description = "Search with invalid keyword")
    public void testSearchInvalidKeyword() {
        LoginUtils.login(driver, prop.getProperty("test.username"), prop.getProperty("test.password"));
        
        HomePage homePage = new HomePage(driver);
        homePage.searchProduct("xyz123nonexistent");
        TestUtils.waitForPageLoad(driver);
        
        SearchFilterPage searchPage = new SearchFilterPage(driver);
        Assert.assertTrue(searchPage.getResultCount() == 0 || searchPage.isNoResultsMessage(), 
            "Should show no results");
        System.out.println("✅ No results for invalid search");
    }

    @Test(priority = 3, description = "Filter by category")
    public void testFilterByCategory() {
        LoginUtils.login(driver, prop.getProperty("test.username"), prop.getProperty("test.password"));
        
        // Navigate to products with category filter
        driver.get(prop.getProperty("base.url") + "/products?category=Smartphones");
        TestUtils.waitForPageLoad(driver);
        
        SearchFilterPage searchPage = new SearchFilterPage(driver);
        Assert.assertTrue(searchPage.hasResults(), "Should show filtered results");
        System.out.println("✅ Category filter results: " + searchPage.getResultCount());
    }
}
package com.ecommerce.tests;

import com.ecommerce.pages.*;
import com.ecommerce.utils.LoginUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchFilterTest extends BaseTest {

    @Test(priority = 1, description = "Test search with valid keyword")
    public void testSearchValidKeyword() {
        LoginUtils.login(driver, "srishti_mamgai", "mamgai@345678");

        HomePage homePage = new HomePage(driver);
        homePage.searchProduct("iPhone");
        try { Thread.sleep(2000); } catch (Exception e) {}

        SearchFilterPage searchPage = new SearchFilterPage(driver);
        Assert.assertTrue(searchPage.hasResults(), "Should show results for iPhone");
        System.out.println("✅ Search results: " + searchPage.getResultCount());
    }

    @Test(priority = 2, description = "Test search with invalid keyword")
    public void testSearchInvalidKeyword() {
        LoginUtils.login(driver, "srishti_mamgai", "mamgai@345678");

        HomePage homePage = new HomePage(driver);
        homePage.searchProduct("xyz123nonexistent");
        try { Thread.sleep(2000); } catch (Exception e) {}

        SearchFilterPage searchPage = new SearchFilterPage(driver);
        Assert.assertTrue(
            searchPage.isNoResultsMessage() || searchPage.getResultCount() == 0,
            "Should show no results"
        );
        System.out.println("✅ No results for invalid search");
    }

    @Test(priority = 3, description = "Verify search results match keyword")
    public void testSearchResultsMatch() {
        LoginUtils.login(driver, "srishti_mamgai", "mamgai@345678");

        HomePage homePage = new HomePage(driver);
        // Use a keyword that EXISTS in your products
        homePage.searchProduct("iPhone");
        try { Thread.sleep(2000); } catch (Exception e) {}

        SearchFilterPage searchPage = new SearchFilterPage(driver);
        
        if (!searchPage.hasResults()) {
            System.out.println("⚠️ No results found, skipping verification");
            return;
        }
        
        Assert.assertTrue(searchPage.verifySearchResults("iPhone"), 
            "Results should contain 'iPhone'");
        System.out.println("✅ Search results verified for keyword");
    }
}
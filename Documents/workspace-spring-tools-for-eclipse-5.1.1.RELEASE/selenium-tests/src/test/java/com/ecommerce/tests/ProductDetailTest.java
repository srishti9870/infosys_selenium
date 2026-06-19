package com.ecommerce.tests;

import com.ecommerce.pages.*;
import com.ecommerce.utils.LoginUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductDetailTest extends BaseTest {

    @Test(priority = 1, description = "Validate product name and price on detail page")
    public void testProductNameAndPrice() {
        LoginUtils.login(driver, "srishti_mamgai", "mamgai@345678");

        HomePage homePage = new HomePage(driver);
        homePage.clickFirstProduct();
        try { Thread.sleep(2000); } catch (Exception e) {}

        ProductDetailPage detailPage = new ProductDetailPage(driver);

        // Validate name
        String name = detailPage.getProductName();
        Assert.assertFalse(name.isEmpty(), "Product name should not be empty");
        System.out.println("✅ Product Name: " + name);

        // Validate price
        String price = detailPage.getProductPrice();
        Assert.assertFalse(price.isEmpty(), "Product price should not be empty");
        Assert.assertTrue(price.contains("₹"), "Price should contain ₹");
        System.out.println("✅ Product Price: " + price);

        // Validate category
        String category = detailPage.getCategory();
        Assert.assertFalse(category.isEmpty(), "Category should not be empty");
        System.out.println("✅ Category: " + category);
    }

    @Test(priority = 2, description = "Verify product navigation - breadcrumb")
    public void testProductNavigation() {
        LoginUtils.login(driver, "srishti_mamgai", "mamgai@345678");

        HomePage homePage = new HomePage(driver);
        homePage.clickFirstProduct();
        try { Thread.sleep(2000); } catch (Exception e) {}

        Assert.assertTrue(
            driver.getCurrentUrl().contains("/product/"),
            "Should be on product detail page"
        );
        System.out.println("✅ Navigated to product detail");

        // Navigate back via breadcrumb
        ProductDetailPage detailPage = new ProductDetailPage(driver);
        detailPage.clickBreadcrumbHome();
        try { Thread.sleep(2000); } catch (Exception e) {}

        Assert.assertTrue(
            driver.getCurrentUrl().contains("/home"),
            "Should navigate back to home"
        );
        System.out.println("✅ Breadcrumb navigation works");
    }

    @Test(priority = 3, description = "Verify add to cart button visible")
    public void testAddToCartVisible() {
        LoginUtils.login(driver, "srishti_mamgai", "mamgai@345678");

        HomePage homePage = new HomePage(driver);
        homePage.clickFirstProduct();
        try { Thread.sleep(2000); } catch (Exception e) {}

        ProductDetailPage detailPage = new ProductDetailPage(driver);
        Assert.assertTrue(detailPage.isAddToCartVisible(), "Add to Cart should be visible");
        Assert.assertTrue(detailPage.isStockStatusVisible(), "Stock status should be visible");
        System.out.println("✅ Add to Cart + Stock Status visible");
    }
    
}
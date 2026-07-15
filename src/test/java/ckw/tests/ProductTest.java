package ckw.tests;

import ckw.base.BaseTest;
import ckw.models.Product;
import ckw.models.ProductDetails;
import ckw.pages.LoginPage;
import ckw.pages.ProductDetailsPage;
import ckw.pages.ProductsPage;
import ckw.utils.LoggerUtil;
import ckw.utils.TestDataReader;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {
    private static final Logger log =
            LoggerUtil.getLogger(ProductTest.class);

    @Test(description = "Verify all products are displayed after successful login")
    public void productListShouldBeDisplayedAfterLogin() {
        log.info("******** Product List Test Started ********");

        LoginPage loginPage = new LoginPage();
        log.info("Logging in with Standard User.");

        loginPage.login(
                TestDataReader.get("standard.username"),
                TestDataReader.get("standard.password")
        );

        ProductsPage productsPage = new ProductsPage();
        log.info("Verifying Products page is displayed.");

        Assert.assertTrue(
                productsPage.isDisplayed(),
                "Products page was not displayed."
        );
        log.info("Products page displayed successfully.");

        log.info("******** Product List Test Passed ********");
    }

    @Test(description = "Verify selected product displays correct name and price")
    public void selectedProductShouldDisplayCorrectDetails() {
        log.info("******** Product Details Test Started ********");

        LoginPage loginPage = new LoginPage();
        log.info("Logging in with Standard User.");

        loginPage.login(
                TestDataReader.get("standard.username"),
                TestDataReader.get("standard.password")
        );

        ProductsPage productsPage = new ProductsPage();

        Assert.assertTrue(productsPage.isDisplayed());

        ProductDetails product =
                productsPage.getProductDetails(Product.BIKE_LIGHT);
        log.info("Verifying product name.");

        Assert.assertEquals(
                product.getName(),
                Product.BIKE_LIGHT.getName(),
                "Incorrect product name displayed."
        );
        log.info("Verifying product price.");

        Assert.assertEquals(
                product.getPrice(),
                Product.BIKE_LIGHT.getPrice(),
                "Incorrect product price displayed."
        );
        log.info("Product details verified successfully.");

        log.info("******** Product Details Test Passed ********");
    }


    @Test(description = "Verify selecting a product opens Product Details page")
    public void selectedProductFromDetailsPageShouldDisplayCorrectDetails() {

        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = new ProductsPage();
        ProductDetailsPage detailsPage = new ProductDetailsPage();

        loginPage.login(
                TestDataReader.get("standard.username"),
                TestDataReader.get("standard.password")
        );

        Assert.assertTrue(productsPage.isDisplayed());

        productsPage.openProduct(Product.BACKPACK);

        Assert.assertTrue(detailsPage.isDisplayed());

        ProductDetails details =
                detailsPage.getProductDetails(Product.BACKPACK);

        Assert.assertEquals(
                details.getName(),
                Product.BACKPACK.getName()
        );

        Assert.assertEquals(
                details.getPrice(),
                Product.BACKPACK.getPrice()
        );
    }
}
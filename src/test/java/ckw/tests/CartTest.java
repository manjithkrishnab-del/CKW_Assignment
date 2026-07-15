package ckw.tests;

import ckw.base.BaseTest;
import ckw.models.Product;
import ckw.models.ProductDetails;
import ckw.pages.CartPage;
import ckw.pages.LoginPage;
import ckw.pages.ProductsPage;
import ckw.utils.LoggerUtil;
import ckw.utils.RandomUtils;
import ckw.utils.TestDataReader;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CartTest extends BaseTest {

    private static final Logger log =
            LoggerUtil.getLogger(LogoutTest.class);

    @Test(description = "Verify user can add a single product to the cart")
    public void userShouldBeAbleToAddSingleProductToCart() {


        log.info("******** Cart Test - Single Product Started ********");

        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = new ProductsPage();
        CartPage cartPage = new CartPage();
        log.info("Logging in with Standard User.");

        loginPage.login(
                TestDataReader.get("standard.username"),
                TestDataReader.get("standard.password")
        );

        Assert.assertTrue(productsPage.isDisplayed());
        log.info("Products page displayed.");

        List<Product> randomProduct =
                RandomUtils.getRandomProducts(1, 1);

        Product product = randomProduct.get(0);
        log.info("Randomly selected product : {}", product.getName());

        ProductDetails expectedProduct =
                productsPage.getProductDetails(product);
        log.info("Adding product to cart.");

        productsPage.addToCart(product);
        log.info("Opening cart.");


        productsPage.openCart();
        log.info("Verifying product in cart.");

        Assert.assertTrue(cartPage.isDisplayed());

        Assert.assertTrue(
                cartPage.verifyProduct(expectedProduct),
                "Selected product is not available in the cart."
        );
        log.info("Single product added successfully.");

        log.info("******** Cart Test Passed ********");
    }

    @Test(description = "Verify user can add multiple random products to the cart")
    public void userShouldBeAbleToAddMultipleProductsToCart() {
        log.info("******** Cart Test - Multiple Products Started ********");

        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = new ProductsPage();
        CartPage cartPage = new CartPage();
        log.info("Logging in with Standard User.");

        loginPage.login(
                TestDataReader.get("standard.username"),
                TestDataReader.get("standard.password")
        );

        Assert.assertTrue(productsPage.isDisplayed());
        log.info("Selecting random products.");
        List<Product> randomProducts =
                RandomUtils.getRandomProducts(2, 5);
        log.info("Capturing product details.");

        List<ProductDetails> expectedProducts =
                productsPage.getProductDetails(randomProducts);

        log.info("Adding products to cart.");

        productsPage.addProductsToCart(randomProducts);
        log.info("Opening cart.");

        productsPage.openCart();

        Assert.assertTrue(cartPage.isDisplayed());

        for (ProductDetails product : expectedProducts) {
            log.info("Verifying : {}", product.getName());

            Assert.assertTrue(
                    cartPage.verifyProduct(product),
                    "Product not found : " + product.getName()
            );

            log.info("Multiple products verified successfully.");

            log.info("******** Cart Test Passed ********");

        }
    }

    @Test(description = "Verify Continue Shopping navigates back to Products page")
    public void continueShoppingShouldNavigateBackToProducts() {

        log.info("******** Continue Shopping Test Started ********");
        log.info("Logging in.");

        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = new ProductsPage();
        CartPage cartPage = new CartPage();

        loginPage.login(
                TestDataReader.get("standard.username"),
                TestDataReader.get("standard.password")
        );

        Assert.assertTrue(productsPage.isDisplayed());

        List<Product> randomProduct =
                RandomUtils.getRandomProducts(1, 1);

        Product product = randomProduct.get(0);

        productsPage.addToCart(product);
        log.info("Opening cart.");

        productsPage.openCart();

        Assert.assertTrue(cartPage.isDisplayed());
        log.info("Clicking Continue Shopping.");

        cartPage.clickContinueShopping();

        Assert.assertTrue(
                productsPage.isDisplayed(),
                "Products page was not displayed."
        );
        log.info("Products page displayed after Continue Shopping.");

        log.info("******** Continue Shopping Test Passed ********");
    }

}
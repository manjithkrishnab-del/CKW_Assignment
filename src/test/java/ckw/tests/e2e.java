package ckw.tests;

import ckw.base.BaseTest;
import ckw.models.Product;
import ckw.models.ProductDetails;
import ckw.pages.*;
import ckw.utils.LoggerUtil;
import ckw.utils.RandomUtils;
import ckw.utils.TestDataReader;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class e2e  extends BaseTest {

    private static final Logger log =
            LoggerUtil.getLogger(e2e.class);

    @Test(description = "Verify complete purchase flow from Product Details page")
    public void userShouldCompletePurchaseFromProductDetailsPage() {

        log.info("******** End-to-End Flow 1 Started ********");

        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = new ProductsPage();
        ProductDetailsPage productDetailsPage = new ProductDetailsPage();
        CartPage cartPage = new CartPage();
        CheckoutPage checkoutPage = new CheckoutPage();
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage();
        CheckoutCompletePage completePage = new CheckoutCompletePage();
        MenuPage menuPage = new MenuPage();

        log.info("Logging in with Standard User.");

        loginPage.login(
                TestDataReader.get("standard.username"),
                TestDataReader.get("standard.password")
        );

        Assert.assertTrue(productsPage.isDisplayed());

        Product product = Product.BACKPACK;

        log.info("Opening product: {}", product.getName());

        productsPage.openProduct(product);

        Assert.assertTrue(productDetailsPage.isDisplayed());

        ProductDetails details =
                productDetailsPage.getProductDetails(product);

        Assert.assertEquals(details.getName(), product.getName());
        Assert.assertEquals(details.getPrice(), product.getPrice());

        log.info("Adding product from Product Details page.");

        productDetailsPage.addToCart();

        log.info("Returning to Products page.");

        productDetailsPage.backToProductsFromDetailPage();

        productsPage.openCart();

        Assert.assertTrue(cartPage.isDisplayed());

        Assert.assertTrue(cartPage.verifyProduct(details));

        log.info("Proceeding to Checkout.");

        cartPage.clickCheckout();

        checkoutPage.continueCheckout(
                TestDataReader.get("firstname"),
                TestDataReader.get("lastname"),
                TestDataReader.get("zipcode")
        );

        Assert.assertTrue(overviewPage.isDisplayed());

        log.info("Finishing checkout.");

        overviewPage.clickFinish();

        Assert.assertTrue(completePage.isDisplayed());

        log.info("Returning to Products page.");

        completePage.clickBackHome();

        log.info("Logging out.");

        menuPage.logoutFromApplication();

        Assert.assertTrue(loginPage.isDisplayed());

        log.info("******** End-to-End Flow 1 Passed ********");
    }

    @Test(description = "Verify complete shopping journey")
    public void userShouldCompleteShoppingJourney() {

        log.info("******** End-to-End Flow 2 Started ********");

        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = new ProductsPage();
        CartPage cartPage = new CartPage();
        CheckoutPage checkoutPage = new CheckoutPage();
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage();
        CheckoutCompletePage completePage = new CheckoutCompletePage();
        MenuPage menuPage = new MenuPage();

        log.info("Logging in.");

        loginPage.login(
                TestDataReader.get("standard.username"),
                TestDataReader.get("standard.password")
        );

        Assert.assertTrue(productsPage.isDisplayed());

        List<Product> firstProducts =
                RandomUtils.getRandomProducts(2,2);

        log.info("Adding first set of products.");

        productsPage.addProductsToCart(firstProducts);

        productsPage.openCart();

        Assert.assertTrue(cartPage.isDisplayed());

        Product removedProduct = firstProducts.get(0);

        log.info("Removing product: {}", removedProduct.getName());

        cartPage.removeProduct(removedProduct);

        Assert.assertTrue(cartPage.isProductRemoved(removedProduct));

        log.info("Continuing shopping.");

        cartPage.clickContinueShopping();

        Assert.assertTrue(productsPage.isDisplayed());

        List<Product> secondProducts =
                RandomUtils.getRandomProductsExcluding(firstProducts,2);

        log.info("Adding second set of products.");

        productsPage.addProductsToCart(secondProducts);

        productsPage.openCart();

        Assert.assertTrue(cartPage.isDisplayed());

        log.info("Proceeding to Checkout.");

        cartPage.clickCheckout();

        checkoutPage.continueCheckout(
                TestDataReader.get("firstname"),
                TestDataReader.get("lastname"),
                TestDataReader.get("zipcode")
        );

        Assert.assertTrue(overviewPage.isDisplayed());

        log.info("Completing purchase.");

        overviewPage.clickFinish();

        Assert.assertTrue(completePage.isDisplayed());

        log.info("Returning to Products page.");

        completePage.clickBackHome();

        log.info("Logging out.");

        menuPage.logoutFromApplication();

        Assert.assertTrue(loginPage.isDisplayed());

        log.info("******** End-to-End Flow 2 Passed ********");
    }

    }




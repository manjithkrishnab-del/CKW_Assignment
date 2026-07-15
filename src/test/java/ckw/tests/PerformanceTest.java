package ckw.tests;

import ckw.base.BaseTest;
import ckw.models.Product;
import ckw.pages.*;
import ckw.utils.LoggerUtil;
import ckw.utils.RandomUtils;
import ckw.utils.TestDataReader;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PerformanceTest extends BaseTest {
    private static final Logger log =
            LoggerUtil.getLogger(PerformanceTest.class);

    @Test(description = "Verify performance_glitch_user can complete checkout successfully")
    public void performanceUserShouldLoginAndCheckoutSuccessfully() {

        log.info("******** Performance User end to end Test Started ********");

        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = new ProductsPage();
        CartPage cartPage = new CartPage();
        CheckoutPage checkoutPage = new CheckoutPage();
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage();
        CheckoutCompletePage completePage = new CheckoutCompletePage();
        MenuPage menuPage = new MenuPage();
        log.info("Logging in with Performance User.");

        loginPage.login(
                TestDataReader.get("performance.username"),
                TestDataReader.get("performance.password")
        );

        Assert.assertTrue(
                productsPage.isDisplayed(),
                "Products page was not displayed."
        );
        log.info("Products page displayed successfully after delayed login.");

        List<Product> randomProducts =
                RandomUtils.getRandomProducts(2, 5);

        productsPage.addProductsToCart(randomProducts);
        log.info("Opening cart.");

        productsPage.openCart();

        Assert.assertTrue(cartPage.isDisplayed());

        cartPage.clickCheckout();
        log.info("Proceeding to checkout.");

        Assert.assertTrue(checkoutPage.isDisplayed());
        log.info("Entering checkout information.");

        checkoutPage.continueCheckout(
                TestDataReader.get("firstname"),
                TestDataReader.get("lastname"),
                TestDataReader.get("zipcode")
        );

        Assert.assertTrue(overviewPage.isDisplayed());
        log.info("Finishing checkout.");

        overviewPage.clickFinish();

        Assert.assertTrue(
                completePage.isOrderSuccessful(),
                "Checkout was not completed successfully."
        );
        menuPage.logoutFromApplication();

        Assert.assertTrue(
                loginPage.isDisplayed(),
                "User was not redirected to Login page after logout."
        );
        log.info("Checkout completed successfully for Performance User.");

        log.info("******** Performance User Test Passed ********");
    }
}
package ckw.tests;

import ckw.base.BaseTest;
import ckw.models.Product;
import ckw.pages.CartPage;
import ckw.pages.CheckoutCompletePage;
import ckw.pages.CheckoutOverviewPage;
import ckw.pages.CheckoutPage;
import ckw.pages.LoginPage;
import ckw.pages.ProductsPage;
import ckw.utils.TestDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test(description = "Verify user can successfully complete checkout")
    public void userShouldCompleteCheckoutSuccessfully() {

        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = new ProductsPage();
        CartPage cartPage = new CartPage();
        CheckoutPage checkoutPage = new CheckoutPage();
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage();
        CheckoutCompletePage completePage = new CheckoutCompletePage();

        loginPage.login(
                TestDataReader.get("standard.username"),
                TestDataReader.get("standard.password")
        );

        Assert.assertTrue(productsPage.isDisplayed());

        productsPage.addToCart(Product.BACKPACK);

        productsPage.openCart();

        Assert.assertTrue(cartPage.isDisplayed());

        cartPage.clickCheckout();

        Assert.assertTrue(checkoutPage.isDisplayed());

        checkoutPage.continueCheckout(
                TestDataReader.get("firstname"),
                TestDataReader.get("lastname"),
                TestDataReader.get("zipcode")
        );

        Assert.assertTrue(overviewPage.isDisplayed());

        overviewPage.clickFinish();

        Assert.assertTrue(completePage.isDisplayed());

        Assert.assertTrue(
                completePage.isOrderSuccessful(),
                "Order confirmation was not displayed."
        );
    }

    @Test(description = "Verify checkout overview page is displayed")
    public void checkoutInformationShouldBeDisplayed() {

        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = new ProductsPage();
        CartPage cartPage = new CartPage();
        CheckoutPage checkoutPage = new CheckoutPage();
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage();

        loginPage.login(
                TestDataReader.get("standard.username"),
                TestDataReader.get("standard.password")
        );

        productsPage.addToCart(Product.BACKPACK);

        productsPage.openCart();

        cartPage.clickCheckout();

        checkoutPage.continueCheckout(
                TestDataReader.get("firstname"),
                TestDataReader.get("lastname"),
                TestDataReader.get("zipcode")
        );

        Assert.assertTrue(
                overviewPage.isDisplayed(),
                "Checkout Overview page was not displayed."
        );
    }

    @Test(description = "Verify user can cancel checkout")
    public void userShouldCancelCheckout() {

        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = new ProductsPage();
        CartPage cartPage = new CartPage();
        CheckoutPage checkoutPage = new CheckoutPage();

        loginPage.login(
                TestDataReader.get("standard.username"),
                TestDataReader.get("standard.password")
        );

        productsPage.addToCart(Product.BACKPACK);

        productsPage.openCart();

        cartPage.clickCheckout();

        checkoutPage.continueCheckout(
                TestDataReader.get("firstname"),
                TestDataReader.get("lastname"),
                TestDataReader.get("zipcode")
        );

        Assert.assertTrue(checkoutPage.isDisplayed());

        checkoutPage.clickCancel();

        Assert.assertTrue(
                cartPage.isDisplayed(),
                "User was not navigated back to Cart page."
        );
    }
}
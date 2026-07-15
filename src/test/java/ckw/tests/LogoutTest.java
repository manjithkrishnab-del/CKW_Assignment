package ckw.tests;

import ckw.base.BaseTest;
import ckw.pages.LoginPage;
import ckw.pages.MenuPage;
import ckw.pages.ProductsPage;
import ckw.utils.LoggerUtil;
import ckw.utils.TestDataReader;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {
    private static final Logger log =
            LoggerUtil.getLogger(LogoutTest.class);


    @Test(description = "Verify user can logout successfully")
    public void userShouldLogoutSuccessfully() {

        log.info("******** Logout Test Started ********");

        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = new ProductsPage();
        MenuPage menuPage = new MenuPage();
        log.info("Logging in with Standard User");

        loginPage.login(
                TestDataReader.get("standard.username"),
                TestDataReader.get("standard.password")
        );

        Assert.assertTrue(productsPage.isDisplayed());
        log.info("Products page displayed successfully.");

        log.info("Opening menu and performing logout.");

        menuPage.logoutFromApplication();

        Assert.assertTrue(
                loginPage.isDisplayed(),
                "User was not redirected to Login page after logout."
        );
        log.info("Logout successful. Login page displayed.");

        log.info("******** Logout Test Passed ********");
    }
}

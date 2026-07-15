package ckw.tests;

import ckw.base.BaseTest;
import ckw.pages.LoginPage;
import ckw.pages.ProductsPage;
import ckw.utils.LoggerUtil;
import ckw.utils.TestDataReader;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private static final Logger log =
            LoggerUtil.getLogger(LoginTest.class);

    @BeforeMethod
    public void pageSetup() {
        log.info("Launching application");
        loginPage = new LoginPage();
        productsPage = new ProductsPage();
    }

    @Test(description = "Verify standard user can login successfully")
    public void standardUserShouldLoginSuccessfully() {
        log.info("Logging in as Standard User");

        loginPage.login(
                TestDataReader.get("standard.username"),
                TestDataReader.get("standard.password")
        );

        Assert.assertTrue(
                productsPage.isDisplayed(),
                "Products page was not displayed after successful login."
        );
        log.info("Successfully Logged in Standard User");
    }

    @Test(description = "Verify locked user cannot login")
    public void lockedUserShouldNotBeAbleToLogin() {
        log.info("Logging in as locked user");

        loginPage.login(
                TestDataReader.get("locked.username"),
                TestDataReader.get("locked.password")
        );

        Assert.assertTrue(
                loginPage.isErrorDisplayed(),
                "Locked user error message was not displayed."
        );

        Assert.assertTrue(
                loginPage.getErrorMessage().contains("sadface"),
                "Unexpected error message: " + loginPage.getErrorMessage()
        );
        log.info("locked user log-in not Successfull");
    }

    @Test(description = "Verify problem user can login successfully")
    public void problemUserShouldLoginSuccessfully() {
        log.info("Logging in as problem user");

        loginPage.login(
                TestDataReader.get("problem.username"),
                TestDataReader.get("problem.password")
        );

        Assert.assertTrue(
                productsPage.isDisplayed(),
                "Products page was not displayed for problem user."
        );
        log.info("Successfully Logged in problem user");
    }

    @Test(description = "Verify performance glitch user can login successfully")
    public void performanceUserShouldLoginSuccessfully() {
        log.info("Logging in as Performance user");

        long startTime = System.currentTimeMillis();

        loginPage.login(
                TestDataReader.get("performance.username"),
                TestDataReader.get("performance.password")
        );

        long endTime = System.currentTimeMillis();

        Assert.assertTrue(
                productsPage.isDisplayed(),
                "Products page was not displayed for performance user."
        );

        log.info("Performance User Login Time : "
                + (endTime - startTime) + " ms");
        log.info("Successfully Logged in Performance user");
    }
}
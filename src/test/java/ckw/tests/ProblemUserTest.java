package ckw.tests;

import ckw.base.BaseTest;
import ckw.models.Product;
import ckw.pages.LoginPage;
import ckw.pages.ProductsPage;
import ckw.utils.LoggerUtil;
import ckw.utils.TestDataReader;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProblemUserTest extends BaseTest {

    private static final Logger log =
            LoggerUtil.getLogger(ProblemUserTest.class);

    @Test(description = "Verify problem_user can login and products are displayed")
    public void problemUserShouldDisplayProducts() {

        log.info("******** Problem User Test Started ********");

        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = new ProductsPage();

        log.info("Logging in with Problem User.");

        loginPage.login(
                TestDataReader.get("problem.username"),
                TestDataReader.get("problem.password")
        );

        Assert.assertTrue(
                productsPage.isDisplayed(),
                "Products page was not displayed."
        );

        log.info("Products page displayed successfully.");

        log.info("Verifying sample products are visible.");

        Assert.assertTrue(productsPage.isProductVisible(Product.BACKPACK));
        Assert.assertTrue(productsPage.isProductVisible(Product.BIKE_LIGHT));

        log.info("Products are displayed successfully.");

        log.info("Known application behavior: Product images may not render correctly for problem_user.");

        log.info("******** Problem User Test Passed ********");
    }
}
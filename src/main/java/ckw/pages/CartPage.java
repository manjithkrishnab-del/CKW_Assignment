package ckw.pages;

import ckw.driver.DriverFactory;
import ckw.models.Product;
import ckw.models.ProductDetails;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {

    //Static

    private final By yourCartTitle =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Your Cart\")");

    private final By checkoutButton =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"CHECKOUT\")");

    private final By continueShoppingButton =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"CONTINUE SHOPPING\")");


    private By productName(String product) {
        return AppiumBy.xpath(
                "//android.widget.TextView[@text='" + product + "']"
        );
    }

    private By productPrice(String product, String price) {
        return AppiumBy.xpath(
                "//android.widget.TextView[@text='" + product + "']" +
                        "/following-sibling::android.widget.TextView[@text='" + price + "']"
        );
    }
    private By removeButton(Product product) {

        return AppiumBy.xpath(
                "//android.widget.TextView[@text='" + product.getName() + "']" +
                        "/following-sibling::android.view.ViewGroup[@content-desc='REMOVE']"
        );
    }


    public boolean isDisplayed() {
        return isDisplayed(yourCartTitle);
    }


    public void clickCheckout() {
        click(checkoutButton);
    }

    public void clickContinueShopping() {
        click(continueShoppingButton);
    }


    public boolean isProductPresent(String product) {
        return isDisplayed(productName(product));
    }

    public boolean verifyProduct(ProductDetails product) {

        return isDisplayed(productName(product.getName())) &&
                isDisplayed(productPrice(
                        product.getName(),
                        product.getPrice()));
    }
    public void removeProduct(Product product) {

        click(removeButton(product));
    }
    public boolean isProductRemoved(Product product) {

        return new WebDriverWait(
                DriverFactory.getDriver(),
                Duration.ofSeconds(10)
        ).until(
                ExpectedConditions.invisibilityOfElementLocated(
                        productName(product.getName())
                )
        );
    }

}
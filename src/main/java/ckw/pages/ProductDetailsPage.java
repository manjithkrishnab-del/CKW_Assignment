package ckw.pages;

import ckw.models.Product;
import ckw.models.ProductDetails;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class ProductDetailsPage extends BasePage {

    private final By backToProductsButton =
            AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"back-button\")");

    private final By addToCartButton =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"ADD TO CART\")");

    private By productName(Product product) {

        return AppiumBy.xpath(
                "//android.widget.TextView[@text='" +
                        product.getName() + "']"
        );
    }

    private By productPrice(Product product) {

        return AppiumBy.xpath(
                "//android.widget.TextView[@text='" + product.getName() +
                        "']/following-sibling::android.widget.TextView[2]"
        );

    }

    public boolean isDisplayed() {
        return isDisplayed(backToProductsButton);
    }

    public ProductDetails getProductDetails(Product product) {

        return new ProductDetails(
                getText(productName(product)),
                getText(productPrice(product))
        );
    }

    public void addToCart() {
        click(addToCartButton);
    }

    public void backToProductsFromDetailPage() {
        click(backToProductsButton);
    }
}
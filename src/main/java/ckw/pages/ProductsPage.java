package ckw.pages;

import ckw.driver.DriverFactory;
import ckw.models.Product;
import ckw.models.ProductDetails;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {


    private final By sortPicker =
            AppiumBy.androidUIAutomator(
                    "new UiSelector().resourceId(\"sort-picker\")"
            );



    private final By cartButton =
            AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"cart-button\")");

    private final By menuButton =
            AppiumBy.id("menu-button");


    private By productName(Product product) {
        return AppiumBy.xpath(
                "//android.widget.TextView[@text='" + product.getName() + "']"
        );
    }

    private By productPrice(Product product) {
        return AppiumBy.xpath(
                "//android.widget.TextView[@text='" + product.getName() +
                        "']/following-sibling::android.widget.TextView[2]"
        );
    }

    private void scrollToText(String text) {

        DriverFactory.getDriver().findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))"
                                + ".scrollIntoView(new UiSelector().text(\""
                                + text
                                + "\"))"
                )
        );
    }
    private By addToCartButton(Product product) {

        return AppiumBy.xpath(
                "//android.widget.TextView[@text='" + product.getName() + "']" +
                        "/following-sibling::android.view.ViewGroup[@content-desc='ADD TO CART']"
        );
    }




    public boolean isDisplayed() {
        return isDisplayed(sortPicker);
    }


    public void openCart() {
        click(cartButton);
    }


    public void addToCart(Product product) {


        scrollToText(product.getName());

        if (product == Product.RED_TSHIRT) {
            // Extra swipe because it's the last item
            swipeUpSmall();
        }

        click(addToCartButton(product));
    }
    public void swipeUpSmall() {

        Dimension size = DriverFactory.getDriver().manage().window().getSize();

        Map<String, Object> params = new HashMap<>();
        params.put("left", size.width / 2);
        params.put("top", size.height / 2);
        params.put("width", 100);
        params.put("height", 500);
        params.put("direction", "up");
        params.put("percent", 0.5);

        ((JavascriptExecutor) DriverFactory.getDriver())
                .executeScript("mobile: swipeGesture", params);
    }

    public void addProductsToCart(List<Product> products) {

        for (Product product : products) {
            addToCart(product);
        }
    }

    public boolean isProductVisible(Product product) {

        return isDisplayed(productName(product));
    }


    public ProductDetails getProductDetails(Product product) {

        scrollToText(product.getName());

        return new ProductDetails(
                getText(productName(product)),
                getText(productPrice(product))
        );
    }

    public List<ProductDetails> getProductDetails(List<Product> products) {

        List<ProductDetails> details = new ArrayList<>();

        for (Product product : products) {
            details.add(getProductDetails(product));
        }

        return details;
    }
    public void openProduct(Product product) {

        click(productName(product));
    }
}
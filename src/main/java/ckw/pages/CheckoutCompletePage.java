package ckw.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CheckoutCompletePage extends BasePage {


    private final By checkoutCompleteTitle =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Checkout: Complete!\")");

    private final By successMessage =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Thank you for your order!\")");

    private final By backHomeButton =
            AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"back-home-button\")");


    public boolean isDisplayed() {
        return isDisplayed(checkoutCompleteTitle);
    }

    public boolean isOrderSuccessful() {
        return isDisplayed(successMessage);
    }


    public void clickBackHome() {
        click(backHomeButton);
    }
}
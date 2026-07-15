package ckw.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CheckoutOverviewPage extends BasePage {


    private final By checkoutOverviewTitle = AppiumBy.androidUIAutomator("new UiSelector().text(\"Checkout: Overview\")");

    private final By finishButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"FINISH\")");

    private final By cancelButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"CANCEL\")");


    public boolean isDisplayed() {
        return isDisplayed(checkoutOverviewTitle);
    }


    public void clickFinish() {
        click(finishButton);
    }

    public void clickCancel() {
        click(cancelButton);
    }
}
package ckw.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CheckoutPage extends BasePage {

    private final By checkoutTitle = AppiumBy.androidUIAutomator("new UiSelector().text(\"Checkout: Your Info\")");
    private final By firstName = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"firstName-input\")");
    private final By lastName = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"lastName-input\")");
    private final By zipCode = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"zipCode-input\")");
    private final By continueButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"CONTINUE\")");
    private final By cancelButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"CONTINUE\")");


    public boolean isDisplayed() {
        return isDisplayed(checkoutTitle);
    }


    public void enterFirstName(String name) {
        type(firstName, name);
    }

    public void enterLastName(String name) {
        type(lastName, name);
    }

    public void enterZipCode(String zip) {
        type(zipCode, zip);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public void clickCancel() {
        click(cancelButton);
    }

    public void enterCheckoutInformation(String first,
                                         String last,
                                         String zip) {

        enterFirstName(first);
        enterLastName(last);
        enterZipCode(zip);
    }

    public void continueCheckout(String first,
                                 String last,
                                 String zip) {

        enterCheckoutInformation(first, last, zip);
        clickContinue();
    }
}
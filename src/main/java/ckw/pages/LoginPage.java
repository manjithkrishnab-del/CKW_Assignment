package ckw.pages;

import ckw.driver.DriverFactory;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;


public class LoginPage extends BasePage {

    private final By usernameInput =
            AppiumBy.xpath("//android.widget.EditText[@hint='Username']");
    private final By passwordInput =
            AppiumBy.xpath("//android.widget.EditText[@hint='Password']");
    private final By loginButton =
            AppiumBy.accessibilityId("LOGIN");
    private final By loginError =
            AppiumBy.xpath(
                    "//android.widget.TextView[@text='Epic sadface: Sorry, this user has been locked out.']"
            );


    public LoginPage() {
        super();
    }

    public void enterUsername(String username) {
        type(usernameInput, username);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);

    }

    public void clickLogin() {
        click(loginButton);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(loginError);
    }

    public String getErrorMessage() {
        return getText(loginError);
    }

    public boolean isDisplayed() {
        return isDisplayed(loginButton);
    }


    public void login(String username, String password) {

//        System.out.println(DriverFactory.getDriver().getPageSource());

        enterUsername(username);
        enterPassword(password);
        clickLogin();


    }


}
package ckw.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class MenuPage extends BasePage {

    private final By menuButton =
            AppiumBy.androidUIAutomator(
                    "new UiSelector().text(\"☰\")"
            );

    private final By logoutButton =
            AppiumBy.androidUIAutomator(
                    "new UiSelector().text(\"Logout\")"
            );

    public void openMenu() {
        click(menuButton);
    }

    public void logout() {
        click(logoutButton);
    }

    public void logoutFromApplication() {
        openMenu();
        logout();
    }
}
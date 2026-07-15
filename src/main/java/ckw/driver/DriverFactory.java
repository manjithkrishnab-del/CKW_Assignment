package ckw.driver;

import ckw.utils.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URI;


public class DriverFactory {

    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static void initializeDriver() {

        String platform = ConfigReader.get("platform");

        if (platform.equalsIgnoreCase("android")) {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setDeviceName(ConfigReader.get("android.deviceName"));
            options.setAutomationName(ConfigReader.get("android.automationName"));
            options.setApp(ConfigReader.get("android.app"));


            try {
                driver.set(new AndroidDriver(URI.create(ConfigReader.get("appium.server")).toURL(), options)

                );
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        else if(platform.equalsIgnoreCase("ios")){
            XCUITestOptions options = new XCUITestOptions();
            options.setPlatformName("ios");
            options.setDeviceName(ConfigReader.get("ios.deviceName"));
            options.setPlatformVersion(ConfigReader.get("ios.platformVersion"));
            options.setAutomationName("XCUITest");
            options.setApp(ConfigReader.get("ios.app"));

            try {
                driver.set(new IOSDriver(
                        URI.create(ConfigReader.get("appium.server")).toURL(), options)

                );
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

        }
        else
        {
            throw new IllegalArgumentException(
                    "Unsupported platform: " + platform
            );
        }

    }

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {

        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }

    }
}
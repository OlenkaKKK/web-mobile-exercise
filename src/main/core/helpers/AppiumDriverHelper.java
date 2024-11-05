package core.helpers;

import bussiness.pageObjects.AndroidChromePage;
import core.ApplicationManager;
import core.Configuration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static core.Configuration.PLATFORM_IOS;
import static core.Configuration.setMutableCapabilities;
import static org.testng.FileAssert.fail;

public class AppiumDriverHelper extends BaseHelper {

    protected static ApplicationManager app = ApplicationManager.get();
    private static AppiumDriver appiumDriver;

    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    public void setUpDriver() {
        appiumDriver = null;
        appiumDriver = initDriver();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
    }

    public boolean isSessionAlive() {
        boolean isSessionAlive = (appiumDriver != null) && (appiumDriver.getSessionId() != null);
        logger.debug("Is session alive: {}", isSessionAlive);
        if (!isSessionAlive)
            logger.warn("Appium session is not run");
        return isSessionAlive;
    }

    private AppiumDriver initDriver() {
        MutableCapabilities capabilities = setMutableCapabilities();

        try {
            URL appiumUrl = new URL(Configuration.APPIUM_URL);
            if (Configuration.PLATFORM_IOS) {
                logger.debug("Initializing iOSDriver with capabilities: {}", capabilities);
                return new IOSDriver(new URL(Configuration.APPIUM_URL), capabilities);
            } else if (Configuration.PLATFORM_ANDROID) {
                logger.debug("Initializing AndroidDriver with capabilities: {}", capabilities);
                return new AndroidDriver(appiumUrl, capabilities);
            } else {
                logger.error("Unsupported platform: {}", Configuration.PLATFORM_NAME);
            }
        } catch (MalformedURLException e) {
            logger.error("Invalid Appium URL: " + Configuration.APPIUM_URL, e);
        } catch (SessionNotCreatedException e) {
            logger.error("Failed to start a new Appium session on " + Configuration.PLATFORM_NAME, e);
        }

        return null;
    }

    public void quit() {
        appiumDriver.quit();
    }

    public void tap(WebElement element) {
        logger.info("Tap element: {}", element);
        Runnable runnable = element::click;
        try {
            runnable.run();
        } catch (Exception e) {
            fail(e.getMessage(), e);
        }
    }

    public String getText(WebElement element) {
        logger.debug("Getting text from WebElement: {}", element);
        String text = getAttributeValue(element, PLATFORM_IOS ? "value" : "text");
        logger.debug("Retrieved text: {}", text);
        return text;
    }

    public String getAttributeValue(WebElement element, String attributeName) {
        logger.debug("Getting attribute '{}' from WebElement: {}", attributeName, element);
        String attributeValue = element.getAttribute(attributeName);
        logger.debug("Retrieved attribute value: {}", attributeValue);
        return attributeValue;
    }

    public void type(WebElement element, String text) {
        logger.debug("Typing: {}{}", element, text);
        element.sendKeys(text);
    }

    public AndroidDriver getAndroidDriver() {
        return (AndroidDriver) getDriver();
    }

    public void goToUrl(String url) {
        logger.debug("Open url={}", url);
        if (PLATFORM_IOS)
            app.appium().getDriver().get(url);
        else {
            app.android()
                    .openChrome()
                    .skipFirstLaunchScreensInChrome();
            new AndroidChromePage().searchPage().goNewTabUrl(url);
        }
    }

}

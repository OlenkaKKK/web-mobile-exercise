package core.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitsHelper extends BaseHelper {

    private final int APPIUM_WAIT = 10;

    public boolean isTextPresent(String text) {
        logger.debug("Checking for text presence: {}", text);
        try {
            // app.appium().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(APPIUM_WAIT));
            app.appium().getDriver().findElement(By.xpath("//*[contains(@text,'" + text + "')] | //*[contains(@value,'" + text + "')]"));
            logger.debug("Text '{}' found.", text);
            return true;
        } catch (NoSuchElementException e) {
            logger.debug("Text '{}' not found.", text);
        } finally {
            app.appium().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(APPIUM_WAIT));
        }
        return false;
    }

    public void waitForVisibility(WebElement element) {
        waitForVisibility(element, APPIUM_WAIT);
    }

    public void waitForVisibility(WebElement element, int timeout) {
        logger.debug("Waiting for visibility of element: {} with timeout: {} seconds", element, timeout);
        try {
            new WebDriverWait(app.appium().getDriver(), Duration.ofSeconds(timeout))
                    .until(ExpectedConditions.visibilityOf(element));
            logger.debug("Element is now visible: {}", element);
        } catch (TimeoutException e) {
            logger.debug("Timeout: Element not visible after {} seconds: {}", timeout, element, e);
        }
    }

    public boolean isElementVisible(WebElement element) {
        logger.debug("Checking if element is visible: {}", element);
        try {
            app.appium().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(APPIUM_WAIT));
            boolean visible = element.isDisplayed();
            logger.debug("Element visibility status: {}", visible);
            return visible;
        } catch (NoSuchElementException e) {
            logger.debug("Element not found: {}", element, e);
            return false;
        } finally {
            app.appium().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(APPIUM_WAIT));
        }
    }

}

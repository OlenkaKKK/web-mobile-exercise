package bussiness.pageObjects;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import static core.helpers.BaseHelper.logger;

public class HomeWebPage extends ObjectBase {

    @AndroidFindBy(accessibility = "Customize")
    @iOSXCUITFindBy(accessibility = "Customize")
    private WebElement customizeButton;

    @AndroidFindBy(xpath = "//*[contains(@text,'Reject All')]")
    @iOSXCUITFindBy(accessibility = "Reject All")
    private WebElement rejectAllButton;

    @AndroidFindBy(accessibility = "Google Play")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeLink[`label == \"Apple Store\"`]")
    private WebElement storeButton;

    @Override
    public HomeWebPage waitUntilLoaded() {
        logger.info("Wait for home page to load");
        app.waits().waitForVisibility(customizeButton);
        return this;
    }

    @Override
    public boolean isOpened() {
        boolean isPageOpened = app.waits().isElementVisible(customizeButton);
        logger.info("Home page is opened : {}", isPageOpened);
        return isPageOpened;
    }

    public void tapRejectAllButton() {
        logger.info("Tap reject all button");
        app.appium().tap(rejectAllButton);
    }

    public void tapStoreButton() {
        logger.info("Tap apple pay button");
        app.appium().tap(storeButton);
    }
}

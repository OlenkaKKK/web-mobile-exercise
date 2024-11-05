package bussiness.pageObjects;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import static core.helpers.BaseHelper.logger;

public class FAQWebPage extends ObjectBase {

    @AndroidFindBy(xpath = "//android.widget.Button[@text = 'Open menu']")
    @iOSXCUITFindBy(accessibility = "Open menu")
    private WebElement openMenuButton;

    @AndroidFindBy(id = "search-bar")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`value == \"Search for articles...\"`]")
    private WebElement searchTextField;

    @Override
    public FAQWebPage waitUntilLoaded() {
        logger.info("Wait for home page to load");
        app.waits().waitForVisibility(searchTextField);
        return this;
    }

    @Override
    public boolean isOpened() {
        boolean isPageOpened = app.waits().isElementVisible(searchTextField);
        logger.info("Home page is opened : {}", isPageOpened);
        return isPageOpened;
    }

    public void tapOpenMenuButton() {
        logger.info("Tap open context menu button");
        app.appium().tap(openMenuButton);
    }
}

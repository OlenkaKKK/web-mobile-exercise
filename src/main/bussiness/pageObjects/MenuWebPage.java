package bussiness.pageObjects;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import static core.helpers.BaseHelper.logger;

public class MenuWebPage extends ObjectBase {

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"This image isn‘t labeled. Open the More Options menu at the top right to get image descriptions.\"]")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label == \"Zilch | Join over four million customers\"`]/XCUIElementTypeLink[1]/XCUIElementTypeLink")
    private WebElement zilchLogoButton;

    @AndroidFindBy(accessibility = "Apply")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == \"Apply\"`]")
    private WebElement applyButton;

    @Override
    public MenuWebPage waitUntilLoaded() {
        logger.info("Wait for Menu page to load");
        app.waits().waitForVisibility(zilchLogoButton);
        return this;
    }

    @Override
    public boolean isOpened() {
        boolean isPageOpened = app.waits().isElementVisible(applyButton);
        logger.info("Menu page is opened : {}", isPageOpened);
        return isPageOpened;
    }

}

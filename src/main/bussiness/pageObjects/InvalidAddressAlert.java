package bussiness.pageObjects;

import core.annotations.iOSOnlyClass;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import static core.helpers.BaseHelper.logger;

@iOSOnlyClass
public class InvalidAddressAlert extends ObjectBase {

    @iOSXCUITFindBy(accessibility = "SFDialogView")
    private WebElement dialogView;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"SFDialogView\"]/XCUIElementTypeOther[1]/XCUIElementTypeTextView")
    private WebElement alertMessageLabel;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == \"OK\"`]")
    private WebElement okButton;


    @Override
    public InvalidAddressAlert waitUntilLoaded() {
        logger.info("Wait for home page to load");
        app.waits().waitForVisibility(dialogView);
        return this;
    }

    @Override
    public boolean isOpened() {
        boolean isPageOpened = app.waits().isElementVisible(dialogView);
        logger.info("Home page is opened : {}", isPageOpened);
        return isPageOpened;
    }

    public String getAlertText() {
        logger.info("Retrieving text value from alert");
        String dialogText = app.appium().getText(alertMessageLabel);
        logger.debug("Retrieved alert text: {}", dialogText);
        return dialogText;
    }
}

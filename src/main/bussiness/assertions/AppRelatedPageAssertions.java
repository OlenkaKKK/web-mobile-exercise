package bussiness.assertions;

import bussiness.pageObjects.GooglePlayPage;
import bussiness.pageObjects.InvalidAddressAlert;
import core.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class AppRelatedPageAssertions {

    public static final Logger logger = LogManager.getLogger();

    public void verifyAlertOrStorePageIsOpen() {
        if (Configuration.PLATFORM_IOS) {
            verifyIosAlertIsOpen();
        } else {
            verifyGooglePlayIsOpen();
        }
    }

    private void verifyIosAlertIsOpen() {
        logger.info("Verifying iOS alert is open.");
        Assert.assertTrue(new InvalidAddressAlert().isOpened());
        Assert.assertTrue(new InvalidAddressAlert().getAlertText()
                        .contains("Safari cannot open the page because the address is invalid."),
                "Dialog text should contain the expected message.");
    }

    private void verifyGooglePlayIsOpen() {
        logger.info("Verifying if Google Play page is open.");
        Assert.assertTrue(new GooglePlayPage().isOpened(), "Google Play is not opened");
    }

}

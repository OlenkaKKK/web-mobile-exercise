package bussiness.bussinessObjects;

import core.Configuration;
import core.constants.Direction;

public class HomeBO extends BusinessObjectsBase {

    public HomeBO rejectAllPrivacy() {
        //TODO: fix cashing
        logger.info("Reject all privacy cookies");
       // page.homeWebPage().tapRejectAllButton();
        return this;
    }

    public void proceedToStoreLink() {
        logger.info("Proceed to Apple Store Link");
        app.actions().scrollToText(Direction.DOWN, Configuration.PLATFORM_IOS ? "Apple Store" : "Google Play", 30);
        page.homeWebPage().tapStoreButton();
    }

}

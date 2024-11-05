package core;

import core.helpers.ActionsHelper;
import core.helpers.AndroidHelper;
import core.helpers.AppiumDriverHelper;
import core.helpers.WaitsHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Centralized point for managing the various helpers
public class ApplicationManager {

    public static final Logger logger = LogManager.getLogger();
    private static final ApplicationManager INSTANCE = new ApplicationManager();
    private AppiumDriverHelper appiumDriverHelper;
    private AndroidHelper androidHelper;
    private ActionsHelper actionsHelper;
    private WaitsHelper waitsHelper;

    public static ApplicationManager get() {
        return INSTANCE;
    }

    public AppiumDriverHelper appium() {
        if (appiumDriverHelper == null)
            appiumDriverHelper = new AppiumDriverHelper();
        return appiumDriverHelper;
    }

    public WaitsHelper waits() {
        if (waitsHelper == null)
            waitsHelper = new WaitsHelper();
        return waitsHelper;
    }

    public ActionsHelper actions() {
        if (actionsHelper == null)
            actionsHelper = new ActionsHelper();
        return actionsHelper;
    }

    public AndroidHelper android() {
        if (androidHelper == null)
            androidHelper = new AndroidHelper();
        return androidHelper;
    }
}

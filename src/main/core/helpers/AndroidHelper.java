package core.helpers;

import bussiness.pageObjects.AndroidChromePage;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AndroidHelper extends BaseHelper {
    private final String chromeAppPackageName = "com.android.chrome";

    public AndroidChromePage openChrome() {
        logger.debug("Openning chrome ");
        launchApp(chromeAppPackageName);
        return new AndroidChromePage();
    }

    public void quitChrome() {
        logger.debug("Quit Chrome");
        terminateApp(chromeAppPackageName);
    }

    public void launchApp(String bundleId) {
        logger.debug(bundleId);
        app.appium().getAndroidDriver().activateApp(bundleId);
    }

    public void terminateApp(String bundleIdOrAppPackage) {
        logger.debug(bundleIdOrAppPackage);
        app.appium().getAndroidDriver().terminateApp(bundleIdOrAppPackage);
    }

    public void pressHardwareKey(AndroidKey key) {
        logger.debug(key);
        app.appium().getAndroidDriver().pressKey(new KeyEvent(key));
    }

}

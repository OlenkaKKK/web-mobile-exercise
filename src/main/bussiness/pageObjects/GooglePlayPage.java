package bussiness.pageObjects;

import core.annotations.AndroidOnlyClass;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import static core.helpers.BaseHelper.logger;

@AndroidOnlyClass
public class GooglePlayPage extends ObjectBase {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Sign in to find the latest Android apps, games, movies, music, & more']")
    private WebElement googlePlayHomeLabel;

    @Override
    public GooglePlayPage waitUntilLoaded() {
        logger.info("Wait for home page to load");
        app.waits().waitForVisibility(googlePlayHomeLabel);
        return this;
    }

    @Override
    public boolean isOpened() {
        boolean isPageOpened = app.waits().isElementVisible(googlePlayHomeLabel);
        logger.info("Home page is opened : {}", isPageOpened);
        return isPageOpened;
    }
}

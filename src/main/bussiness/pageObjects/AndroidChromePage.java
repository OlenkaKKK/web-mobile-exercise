package bussiness.pageObjects;

import core.annotations.AndroidOnlyClass;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import static core.helpers.BaseHelper.logger;

@AndroidOnlyClass
public class AndroidChromePage extends ObjectBase {

    @AndroidFindBy(xpath = "//*[contains(@resource-id, 'com.android.chrome:id')]")
    private WebElement pageTitleText;

    @AndroidFindBy(id = "com.android.chrome:id/url_bar")
    private WebElement urlArea;

    private WelcomePage welcomePage;
    private SignInPage signInPage;
    private SearchPage searchPage;

    public WelcomePage welcomePage() {
        if (welcomePage == null)
            welcomePage = new WelcomePage();
        return welcomePage;
    }

    public SignInPage signInPage() {
        if (signInPage == null)
            signInPage = new SignInPage();
        return signInPage;
    }

    public SearchPage searchPage() {
        if (searchPage == null)
            searchPage = new SearchPage();
        return searchPage;
    }

    public void skipFirstLaunchScreensInChrome() {
        logger.info("Skip first launch page chrome");
        if (welcomePage().isOpened()) {
            welcomePage()
                    .tapAcceptAndContinue();
            signInPage().tapNoThanks();
        }
    }

    public String getUrl() {
        logger.info("get url");
        String url = app.appium().getAttributeValue(urlArea, "text");
        logger.info(url);
        return url;
    }

    @Override
    public AndroidChromePage waitUntilLoaded() {
        logger.info("Wait");
        app.waits().waitForVisibility(pageTitleText);
        return this;
    }

    @Override
    public boolean isOpened() {
        return false;
    }

    public class WelcomePage extends AndroidChromePage {

        public final String pageName = "Welcome to Chrome";

        @AndroidFindBy(xpath = "//android.widget.TextView[@text='" + pageName + "']")
        private WebElement pageTitleText;

        @AndroidFindBy(id = "com.android.chrome:id/terms_accept")
        private WebElement acceptAndContinueButton;

        @Override
        public WelcomePage waitUntilLoaded() {
            logger.info("Wait for Welcome page");
            app.waits().waitForVisibility(pageTitleText);
            return this;
        }

        @Override
        public boolean isOpened() {
            logger.info("Is opened welcome page");
            boolean isPageOpened = app.waits().isElementVisible(pageTitleText);
            logger.info(isPageOpened);
            return isPageOpened;
        }

        public void tapAcceptAndContinue() {
            logger.info(acceptAndContinueButton);
            app.appium().tap(acceptAndContinueButton);
        }
    }

    public class SignInPage extends AndroidChromePage {

        public final String pageName = "Sign in to Chrome";

        @AndroidFindBy(xpath = "//android.widget.TextView[@text='" + pageName + "']")
        private WebElement pageTitleText;

        @AndroidFindBy(id = "com.android.chrome:id/negative_button")
        private WebElement noThanksButton;

        @Override
        public SignInPage waitUntilLoaded() {
            logger.info("Wait for Sign in");
            app.waits().waitForVisibility(pageTitleText);
            return this;
        }

        @Override
        public boolean isOpened() {
            logger.info("Sign IN page");
            boolean isPageOpened = app.waits().isElementVisible(pageTitleText);
            logger.info(isPageOpened);
            return isPageOpened;
        }

        public void tapNoThanks() {
            logger.info(noThanksButton);
            app.appium().tap(noThanksButton);
            new SearchPage().waitUntilLoaded();
        }
    }

    public class SearchPage extends AndroidChromePage {

        @AndroidFindBy(id = "com.android.chrome:id/search_box_text")
        private WebElement searchInput;

        @AndroidFindBy(id = "com.android.chrome:id/url_bar")
        private WebElement searchInputUrl;

        @Override
        public SearchPage waitUntilLoaded() {
            logger.info("Wait");
            app.waits().waitForVisibility(searchInput);
            return this;
        }

        @Override
        public boolean isOpened() {
            logger.info("Search page");
            boolean isPageOpened = app.waits().isElementVisible(searchInput);
            logger.info(isPageOpened);
            return isPageOpened;
        }

        public void goNewTabUrl(String url) {
            logger.info(url);
            //hardcoded tap on new tab button
            app.actions().tap(100, 200);
            app.appium().type(searchInput, url);
            app.android().pressHardwareKey(AndroidKey.ENTER);
        }

    }
}
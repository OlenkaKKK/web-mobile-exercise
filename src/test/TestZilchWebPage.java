import bussiness.assertions.AppRelatedPageAssertions;
import bussiness.pageObjects.MenuWebPage;
import core.annotations.Bug;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
The TestZilchWebPage class contains test cases that validate interactions on the Zilch web page.
The tests include verifying the behavior of the Apple Store link on the homepage and the context
menu functionality on the FAQ page.
 */
public class TestZilchWebPage extends TestBase {

    /*
        Description: This test case checks if the "Download on the Apple Store" link opens correctly on Safari.

        Preconditions:
        The test runs in an emulator environment (where accessing the Apple Store is unsupported).
        The user is on the Zilch homepage: https://www.zilch.com/uk/.

        Steps:
        1. Navigate to the Zilch homepage.
        2. Click on the Apple Store / Google Play download link.

        Expected Result(IOS): Ideally, the Apple Store page opens.
        However, due to emulator limitations(?), a popup displays: "Safari cannot open the page because the address is invalid."
        I made it an expected result for demonstration purposes only as I am not sure if it's a bug or feature :)

        Expected Result(Android): the Google Play opens.
     */
    @Test(description = "Verifies that clicking the Apple Store link on an emulator shows an error popup indicating it cannot open.")
    public void testAppleStoreLinkDisplaysErrorOnEmulator() {
        app.appium().goToUrl("https://www.zilch.com/uk/");
        bo.homePageBO
                .rejectAllPrivacy()
                .proceedToStoreLink();
        new AppRelatedPageAssertions().verifyAlertOrStorePageIsOpen();
    }

    /*
        Description: This test case validates the functionality of the context menu button on the FAQ page.
        It specifically checks for a known bug where the context menu button is present but does not open the menu when clicked.

        Preconditions:
        The user is on the Zilch FAQ page: https://help.payzilch.com/en/.

        Steps:
        Click context menu button.

        Expected Result: The context menu should open when the context menu button is clicked.
        Bug Reference: A bug causes the context menu to not open despite the button being present.
        The issue is noted with a @Bug annotation in the test method.
     */
    @Bug("https://jira.linkToBug")
    @Test(description = "Checks if the FAQ context menu button opens the menu")
    public void testFAQContextMenuButtonFunctionality() {
        app.appium().goToUrl("https://help.payzilch.com/en//");
        bo.faqBO.openContextMenu();
        Assert.assertTrue(new MenuWebPage().isOpened(), "Context Menu is not opened");
    }

}

import bussiness.bussinessObjects.BusinessStrategy;
import core.ApplicationManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {

    protected ApplicationManager app = ApplicationManager.get();
    protected BusinessStrategy bo;

    @BeforeClass(alwaysRun = true)
    public void startDriver() {
        new core.Configuration().load();
        runDriver();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        stopDriver();
    }

    private void runDriver() {
        app.appium().setUpDriver();
    }

    private void stopDriver() {
        if (app.appium().isSessionAlive())
            app.appium().quit();
    }
}

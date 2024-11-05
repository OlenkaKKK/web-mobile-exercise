package bussiness.pageObjects;

import core.ApplicationManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class ObjectBase {

    protected AppiumDriver driver;
    protected ApplicationManager app = ApplicationManager.get();

    public ObjectBase() {
        this.driver = ApplicationManager.get().appium().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public abstract ObjectBase waitUntilLoaded();

    public abstract boolean isOpened();

}
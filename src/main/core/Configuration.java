package core;

import core.helpers.PropertiesHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;

public class Configuration {
    private static final Logger logger = LogManager.getLogger();

    private static final String PLATFORM_FILE_PATH = "./src/main/resources/properties/platform.properties";
    private static final String ANDROID_FILE_PATH = "./src/main/resources/properties/android.properties";
    private static final String IOS_FILE_PATH = "./src/main/resources/properties/ios.properties";

    public static String PLATFORM_NAME;
    public static String APPIUM_URL;
    public static String APPIUM_DEVICE_NAME;
    public static String APPIUM_AUTOMATION_NAME;
    public static String APPIUM_PLATFORM_VERSION;

    public static boolean PLATFORM_ANDROID;
    public static boolean PLATFORM_IOS;

    public static MutableCapabilities setMutableCapabilities() {
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("appium.url", APPIUM_URL);
        capabilities.setCapability("platformName", PLATFORM_NAME);
        capabilities.setCapability("appium:deviceName", APPIUM_DEVICE_NAME);
        capabilities.setCapability("appium:automationName", APPIUM_AUTOMATION_NAME);
        capabilities.setCapability("appium:platformVersion", APPIUM_PLATFORM_VERSION);

        logger.debug("Mutable capabilities set: {}", capabilities.asMap());
        return capabilities;
    }

    public void load() {
        loadPlatformProperties();
        loadAppiumProperties();

        logger.info("Running on device: {}, platform: {}", APPIUM_DEVICE_NAME, PLATFORM_NAME);
    }

    private void loadPlatformProperties() {
        logger.debug("Loading platform properties from: {}", PLATFORM_FILE_PATH);
        PropertiesHelper.loadFile(PLATFORM_FILE_PATH);
        PLATFORM_NAME = PropertiesHelper.getPropertyValueAsResource("platform.name", "properties/platform.properties");

        PLATFORM_IOS = "ios".equalsIgnoreCase(PLATFORM_NAME);
        PLATFORM_ANDROID = "android".equalsIgnoreCase(PLATFORM_NAME);
        logger.info("Loaded property - platform.name: {}", PLATFORM_NAME);
    }

    private void loadAppiumProperties() {
        String filePath = PLATFORM_IOS ? IOS_FILE_PATH : ANDROID_FILE_PATH;
        String resourceName = PLATFORM_IOS ? "properties/ios.properties" : "properties/android.properties";
        logger.debug("Loading Appium properties from: {}", filePath);

        PropertiesHelper.loadFile(filePath);
        APPIUM_URL = PropertiesHelper.getPropertyValueAsResource("appium.url", resourceName);
        APPIUM_AUTOMATION_NAME = PropertiesHelper.getPropertyValueAsResource("appium.automationName", resourceName);
        APPIUM_DEVICE_NAME = PropertiesHelper.getPropertyValueAsResource("appium.deviceName", resourceName);
        APPIUM_PLATFORM_VERSION = PropertiesHelper.getPropertyValueAsResource("appium.platformVersion", resourceName);

        logger.info("Loaded properties - appium.url: {}, appium.automationName: {}, appium.deviceName: {}, appium.platformVersion: {}",
                APPIUM_URL, APPIUM_AUTOMATION_NAME, APPIUM_DEVICE_NAME, APPIUM_PLATFORM_VERSION);
    }

}


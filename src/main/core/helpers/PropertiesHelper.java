package core.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper extends BaseHelper {
    private static String filePath;

    public static String getPropertyValueAsResource(String propertyName, String resourceName) {
        String propertyValue;
        try {
            propertyValue = loadAsResource(propertyName, resourceName);
        } catch (NullPointerException e) {
            throw new RuntimeException("Cannot load a property with name \"" + propertyName + "\" from file environment.properties ", e);
        }
        return propertyValue;
    }

    private static String loadAsResource(String propertyName, String resourceName) {
        String propertyValue = null;
        try {
            if (propertyName != null) {
                Properties properties = new Properties();
                ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                InputStream inputStream = classloader.getResourceAsStream(resourceName);
                properties.load(inputStream);
                propertyValue = properties.getProperty(propertyName);
            } else {
                throw new NullPointerException();
            }
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Cannot load a property with name \"" + propertyName + "\" from file environment.properties", e);
        }
        return propertyValue;
    }

    public static void loadFile(String propertiesFilePath) {
        filePath = propertiesFilePath;
    }

}

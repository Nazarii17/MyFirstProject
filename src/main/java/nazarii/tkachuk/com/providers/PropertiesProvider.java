package nazarii.tkachuk.com.providers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PropertiesProvider {

    private static final String CONFIG_FILE_PATH = "D:\\IdeaProjects\\DrugsApp\\drug_bang\\src\\main\\resources\\config.properties";

    private static Properties properties;

    private static Properties getProperties() {
        if (properties == null) {
            properties = loadProperties(CONFIG_FILE_PATH);
        }
        return properties;
    }

    private static Properties loadProperties(final String filePath) {

        try (InputStream input = new FileInputStream(filePath)) {

            Properties properties = new Properties();
            properties.load(input);
            return properties;

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to find the configuration file " + filePath, e);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load configuration properties", e);
        }
    }

    public static String getProperty(String propertyName) {
        String property = getProperties().getProperty(propertyName);
        if (property == null) {
            System.err.println("Unable to find property '" + propertyName + "' in the configuration file " + CONFIG_FILE_PATH);
        }
        return property;
    }
}

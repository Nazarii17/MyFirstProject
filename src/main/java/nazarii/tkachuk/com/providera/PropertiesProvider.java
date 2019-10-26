package nazarii.tkachuk.com.providera;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesProvider {

    public String getPropertiesValue(String propertyName) {
        String propertyValue = "";
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("D:\\IdeaProjects\\DrugsApp\\drug_bang\\src\\main\\resources\\config.properties")) {
            properties.load(fileInputStream);
            propertyValue = properties.getProperty(propertyName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyValue;
    }
}

package ru.job4j.tracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by User2 on 16.06.2018.
 */
public class PropertiesManager {
    private final String fileName;
    private Map<String, String> propertiesMap = new HashMap<>();
    private boolean isLoaded = false;

    public PropertiesManager(String propFileName, String... propNames) {
        this.fileName = propFileName;
        for (String s : propNames) {
            this.propertiesMap.put(s, null);
        }
    }

    public void loadProps() {
        ClassLoader classLoader = PropertiesManager.class.getClassLoader();
        File propertiesFile = new File(classLoader.getResource("properties.properties").getFile());
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(propertiesFile);
            properties.load(input);
            for (Map.Entry<String, String> pair : propertiesMap.entrySet()) {
                pair.setValue((String) properties.get(pair.getKey()));
            }
            this.isLoaded = true;
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getProperty(String propertyName) {
        if (!this.isLoaded) {
            loadProps();
        }
        return propertiesMap.get(propertyName);
    }
}

package ru.job4j.tracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by User2 on 16.06.2018.
 */
public class PropertiesUtils {
    private static String dbURL, userName, password, initQuery;

    private PropertiesUtils() {
    }

    public static void loadProps() {
        ClassLoader classLoader = PropertiesUtils.class.getClassLoader();
        File propertiesFile = new File(classLoader.getResource("properties.properties").getFile());
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(propertiesFile);
            properties.load(input);
            dbURL = (String) properties.get("dbURL");
            userName = (String) properties.get("userName");
            password = (String) properties.get("password");
            initQuery = (String) properties.get("initQuery");
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

    public static String getDbURL() {
        if (dbURL == null) {
            loadProps();
        }
        return dbURL;
    }

    public static String getUserName() {
        if (userName == null) {
            loadProps();
        }
        return userName;
    }

    public static String getPassword() {
        if (password == null) {
            loadProps();
        }
        return password;
    }

    public static String getInitQuery() {
        return initQuery;
    }
}

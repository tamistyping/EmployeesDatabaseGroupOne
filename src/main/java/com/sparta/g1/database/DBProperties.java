package com.sparta.g1.database;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DBProperties {

    public static String getConnectionProperty(String property) {
        Properties properties = new Properties();

        try {
            properties.load(new FileReader("src/main/resources/mysql.properties"));
            if (property.equals("url")) {
                property = properties.getProperty("url");
            }
            if (property.equals("username")) {
                property = properties.getProperty("username");
            }
            if (property.equals("password")) {
                property = properties.getProperty("password");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }
}

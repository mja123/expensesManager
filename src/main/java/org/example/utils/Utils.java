package org.example.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utils {
    private static final Properties properties;
    private static final String path;

    static {
        properties = new Properties();
        path = "./src/main/resources/variables.properties";
    }

    public static String getProperty(String value) {
        try (FileInputStream inputStream = new FileInputStream(path)) {
            properties.load(inputStream);
            return properties.getProperty(value);
        } catch (IOException e) {
            throw new RuntimeException("Property " + value + " not found");
        }
    }
}

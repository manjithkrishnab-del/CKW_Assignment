package ckw.utils;

import java.io.InputStream;
import java.util.Properties;

public class TestDataReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = TestDataReader.class.getClassLoader()
                .getResourceAsStream("users.properties")) {

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Unable to load users.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
package utils;

import lombok.SneakyThrows;

import java.util.Properties;

public class SuiteConfig {
    private static final String APPLICATION_PROPERTIES = "/application.properties";

    private final Properties properties;

    @SneakyThrows
    public SuiteConfig() {
        properties = new Properties();
        properties.load(SuiteConfig.class.getResourceAsStream(System.getProperty("application.properties", APPLICATION_PROPERTIES)));
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }
}

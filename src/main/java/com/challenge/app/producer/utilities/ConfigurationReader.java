package com.challenge.app.producer.utilities;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationReader {	private static final Logger logger = Logger.getLogger(ConfigurationReader.class);
    private static Properties prop = new Properties();
    public static Properties readPropertyFile() throws Exception {
        if (prop.isEmpty()) {
            try (InputStream input = ConfigurationReader.class.getClassLoader().getResourceAsStream("producer.properties")) {
                prop.load(input);
            } catch (IOException ex) {
                logger.error(ex);
                throw ex;
            }
        }
        return prop;
    }
}

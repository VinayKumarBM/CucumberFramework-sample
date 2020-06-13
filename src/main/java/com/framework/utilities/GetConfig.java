package com.framework.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetConfig {
	private static final Logger log = LoggerFactory.getLogger(GetConfig.class.getName());

    @SuppressWarnings("finally")
    public static String getConfigProperty(String property) {
        String propertyValue = "";
        // If no inputs passed in, look for a configuration file
        String configFile = System.getProperty("user.dir") + "/Configuration.cnf";
        try {
            InputStream configFileStream = new FileInputStream(configFile);
            Properties p = new Properties();
            p.load(configFileStream);
            configFileStream.close();

            propertyValue = (String) p.get(property);

        } catch (Exception e) { // IO or NullPointer exceptions possible in
            // block above
            log.info(String.format("Error reading Config file! please fix and rebuild!"));
            e.printStackTrace();
            System.exit(1);
        } finally {
            return propertyValue;
        }
    }

    public String getTitleFromLink (String property) {
        Properties prop = new Properties();
        String configFile = System.getProperty("user.dir") + "\\link_pagetitle_map.cnf";
        InputStream input = null;
        try {
            input = new FileInputStream(configFile);
            prop.load(input);
            final String value = prop.getProperty(property);
            return (value);
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
        return null;
    }
    @Test
    public void testGetTitle() {
        final String link="Legal & Privacy";
        GetConfig getConfig = new GetConfig();
        final String title = getConfig.getTitleFromLink(link);
        System.out.println(title);
    }
}

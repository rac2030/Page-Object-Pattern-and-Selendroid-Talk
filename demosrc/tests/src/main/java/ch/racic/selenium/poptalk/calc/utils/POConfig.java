/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 * This code has been developed as part of talk on selendroid by Michel Racic (http://rac.su/+) from GDG Zürich (http://www.gdgzh.ch).
 */

package ch.racic.selenium.poptalk.calc.utils;

import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class POConfig {

    private Properties props = new Properties();

    public POConfig() throws IOException {
        InputStream in = POConfig.class.getResourceAsStream("/testconfig.properties");
        try {
            props.load(in);
        } finally {
            in.close();
        }
    }

    public POConfig(String configName) throws IOException {
        InputStream in = POConfig.class.getResourceAsStream("/" + configName + ".properties");
        try {
            props.load(in);
        } finally {
            in.close();
        }

    }

    public <T> Class getPageObjectImplementation(WebDriver driver, Class<T> pageInterfaceToProxy) throws Exception {
        // Use reflections to load the given class
        ClassLoader classLoader = POConfig.class.getClassLoader();
        String propertyName = pageInterfaceToProxy.getSimpleName();
        String browserToUse = props.getProperty("browserToUse");
        if(props.containsKey(browserToUse + "." + propertyName)) {
            propertyName = browserToUse + "." + propertyName;
        }
        Class impl = classLoader.loadClass(props.getProperty(propertyName));

        return impl;
    }

    public String getProperty(String propertyName) {
        return props.getProperty(propertyName);
    }

}

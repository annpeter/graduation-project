package cn.annpeter.graduation.project.base.common;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created on 2016/10/10
 *
 * @author annpeter.it@gmail.com
 */
public class PropertyUtils {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("config", Locale.getDefault());

    public static String getProperty(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            throw new RuntimeException("can't load context file, key: " + key, e);
        }
    }

    public static Integer getIntProperty(String key) {
        return Integer.valueOf(getProperty(key));
    }

    public static boolean getBooleanProperty(String key) {
        return Boolean.valueOf(getProperty(key));
    }
}

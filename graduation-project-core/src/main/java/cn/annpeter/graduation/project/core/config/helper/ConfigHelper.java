package cn.annpeter.graduation.project.core.config.helper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2017/04/06
 *
 * @author annpeter.it@gmail.com
 */
public class ConfigHelper {

    private static final Logger logger = LoggerFactory.getLogger(ConfigHelper.class);

    private static String configDir = null;

    public static String resolveConfigFilePath(String fileName) {
        String prefix = StringUtils.isEmpty(configDir) ? "classpath:/" : "file:"
                + StringUtils.appendIfMissing(configDir, "/");
        String path = prefix + fileName;
        logger.info("load config file {}.", path);
        return path;
    }

    public static void setConfigDir(String configDir) {
        ConfigHelper.configDir = configDir;
    }
}

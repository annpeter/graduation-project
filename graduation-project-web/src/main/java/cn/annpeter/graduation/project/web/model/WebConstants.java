package cn.annpeter.graduation.project.web.model;

import cn.annpeter.graduation.project.base.common.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created on 2017/03/12
 *
 * @author annpeter.it@gmail.com
 */
public class WebConstants {
    public static final String LOGIN_USER_FLAG = "LOGIN_USER_FLAG";
    public static final String LOGIN_USER_INFO = "LOGIN_USER_INFO";

    public static final String File_UPLOAD_BASE_DIR = StringUtils
            .appendIfMissing(PropertyUtils.getProperty("file.upload.base.dir"), "/", "/");
    public static final String FILE_UPLOAD_URL_FREFIX = StringUtils
            .appendIfMissing(PropertyUtils.getProperty("file.upload.url.prefix"), "/", "/");

}

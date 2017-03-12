package cn.annpeter.graduation.project.base.common;


import cn.annpeter.graduation.project.base.common.exception.FileIoException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created on 2016/09/28
 *
 * @author annpeter.it@gmail.com
 */
public class FileUtil {

    public static Properties loadProperties(String path) {
        return loadProperties(path, "UTF-8");
    }

    public static Properties loadProperties(String path, String charsetName) {
        Properties properties = new Properties();
        InputStreamReader isr;
        try {
            isr = new InputStreamReader(loadAsInputStream(path), charsetName);
            BufferedReader bf = new BufferedReader(isr);
            properties.load(bf);
        } catch (IOException e) {
            throw new FileIoException("file parse error.", e);
        }

        return properties;
    }

    public static String loadAsString(String path) {
        return loadAsString(path, "UTF-8");
    }

    public static String loadAsString(String path, String charsetName) {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream inputStream = loadAsInputStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charsetName));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException ignored) {
            throw new RuntimeException("read file error." + path);
        }
        return stringBuilder.toString();
    }

    public static InputStream loadAsInputStream(String path) {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        if (stream == null) {
            throw new FileIoException("file not exist or load error." + path);
        }
        return stream;
    }
}
package cn.annpeter.graduation.project.base.common.util;

import cn.annpeter.graduation.project.base.common.exception.CommonException;
import cn.annpeter.graduation.project.base.common.model.ResultCodeEnum;

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
public class FileUtils {

    public static Properties loadProperties(String path) {
        return loadProperties(path, "UTF-8");
    }

    public static String loadAsString(String path) {
        return loadAsString(path, "UTF-8");
    }

    public static Properties loadProperties(String path, String charsetName) {
        Properties properties = new Properties();
        try (InputStream inputStream = loadAsInputStream(path);
             InputStreamReader isr = new InputStreamReader(inputStream, charsetName);
             BufferedReader bf = new BufferedReader(isr)) {

            properties.load(bf);
        } catch (IOException e) {
            throw new CommonException(ResultCodeEnum.UNKNOWN_ERROR, "配置文件解析失败。", e);
        }

        return properties;
    }

    public static String loadAsString(String path, String charsetName) {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = loadAsInputStream(path);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charsetName);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException ignored) {
            throw new RuntimeException("文件读取异常---" + path);
        }
        return stringBuilder.toString();
    }

    public static InputStream loadAsInputStream(String path) {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        if (stream == null) {
            throw new CommonException(ResultCodeEnum.UNKNOWN_ERROR, "配置文件加载失败或文件不存在---" + path);
        }
        return stream;
    }
}
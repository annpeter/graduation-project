package cn.annpeter.graduation.project.base.common.util;

import cn.annpeter.graduation.project.base.common.exception.SerializationErrorException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created on 2017/03/21
 *
 * @author annpeter.it@gmail.com
 */
public class JsonSnakeCaseUtils {

    private static final ObjectMapper objectMapper = new CustomObjectMapper();

    private static class CustomObjectMapper extends ObjectMapper {

        private static final String dateFormatPattern = "yyyy-MM-dd HH:mm:ss";

        public CustomObjectMapper() {
            // 设置时间格式
            this.setDateFormat(new SimpleDateFormat(dateFormatPattern));

            // 驼峰命名法转换为小写加下划线
            this.setPropertyNamingStrategy(new com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy());

            // 反序列化时可能出现bean对象中没有的字段, 忽略
            this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // 设置序列化的可见域
            this.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.PUBLIC_ONLY);
        }
    }

    public static JsonNode parseString2JsonNode(String jsonStr) {
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(jsonStr);
        } catch (IOException e) {
            throw new SerializationErrorException("deserialize error.", e);
        }
        return jsonNode;
    }

    public static JsonNode parseBytes2JsonNode(byte[] bytes) {
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(bytes);
        } catch (IOException e) {
            throw new SerializationErrorException("deserialize error.", e);
        }
        return jsonNode;
    }

    public static <T> T parseString2Object(String jsonStr, Class<T> clazz) {
        T t;
        try {
            t = objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            throw new SerializationErrorException("deserialize error.", e);
        }
        return t;
    }

    public static <T> T parseBytes2Object(byte[] bytes, Class<T> clazz) {
        T t;
        try {
            t = objectMapper.readValue(bytes, clazz);
        } catch (IOException e) {
            throw new SerializationErrorException("deserialize error.", e);
        }
        return t;
    }

    public static String parseObject2String(Object obj) {
        String s;
        try {
            s = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new SerializationErrorException("serialize error.", e);
        }
        return s;
    }

    public static byte[] parseObject2Bytes(Object obj) {
        byte[] bytes;
        try {
            bytes = objectMapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            throw new SerializationErrorException("serialize error.", e);
        }
        return bytes;
    }
}

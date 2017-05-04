package cn.annpeter.graduation.project.base.common.util;

import cn.annpeter.graduation.project.base.common.exception.SerializationErrorException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created on 2016/10/10
 *
 * @author annpeter.it@gmail.com
 */
public class JsonUtils {
    private static final ObjectMapper objectMapper = new CustomObjectMapper();

    private static class CustomObjectMapper extends ObjectMapper {
        private static final String dateFormatPattern = "yyyy-MM-dd HH:mm:ss";

        public CustomObjectMapper() {
            // 设置时间格式
            this.setDateFormat(new SimpleDateFormat(dateFormatPattern));

            // 反序列化时可能出现bean对象中没有的字段, 忽略
            this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // 设置序列化的可见域
            this.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.PUBLIC_ONLY);
        }
    }

    public static JsonNode string2JsonNode(String jsonStr) {
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(jsonStr);
        } catch (IOException e) {
            throw new SerializationErrorException("deserialize error.", e);
        }
        return jsonNode;
    }

    public static JsonNode bytes2JsonNode(byte[] bytes) {
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(bytes);
        } catch (IOException e) {
            throw new SerializationErrorException("deserialize error.", e);
        }
        return jsonNode;
    }

    public static <T> T string2Object(String jsonStr, Class<T> clazz) {
        T t;
        try {
            t = objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            throw new SerializationErrorException("deserialize error.", e);
        }
        return t;
    }

    public static <T> T string2Object(String jsonString, TypeReference type) {
        try {
            return objectMapper.readValue(jsonString, type);
        } catch (IOException e) {
            throw new SerializationErrorException("deserialize error.", e);
        }
    }

    public static <T> T bytes2Object(byte[] bytes, Class<T> clazz) {
        T t;
        try {
            t = objectMapper.readValue(bytes, clazz);
        } catch (IOException e) {
            throw new SerializationErrorException("deserialize error.", e);
        }
        return t;
    }

    public static String object2String(Object obj) {
        String s;
        try {
            s = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new SerializationErrorException("serialize error.", e);
        }
        return s;
    }

    public static String object2PrettyString(Object obj) {
        String s;
        try {
            s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new SerializationErrorException("serialize error.", e);
        }
        return s;
    }

    public static byte[] object2Bytes(Object obj) {
        byte[] bytes;
        try {
            bytes = objectMapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            throw new SerializationErrorException("serialize error.", e);
        }
        return bytes;
    }
}

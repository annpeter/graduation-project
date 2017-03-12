package cn.annpeter.graduation.project.base.common;

import cn.annpeter.graduation.project.base.common.exception.SerializationErrorException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created on 2016/10/10
 *
 * @author annpeter.it@gmail.com
 */
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode parseString2JsonNode(String jsonStr) {
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(jsonStr);
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

package cn.annpeter.graduation.project.base.restful.rpc.utils;

import cn.annpeter.graduation.project.base.common.exception.SerializationErrorException;
import cn.annpeter.graduation.project.base.restful.rpc.serialization.CustomObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * Created on 2017/02/20
 *
 * @author annpeter.it@gmail.com
 */
public class CustomObjectMapperHelper {
    private static final CustomObjectMapper objectMapper = new CustomObjectMapper();

    public static <T> T parseBytes2Object(byte[] bytes, Class<T> clazz) {
        T t;
        try {
            t = objectMapper.readValue(bytes, clazz);
        } catch (IOException e) {
            throw new SerializationErrorException("deserialize error.", e);
        }
        return t;
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

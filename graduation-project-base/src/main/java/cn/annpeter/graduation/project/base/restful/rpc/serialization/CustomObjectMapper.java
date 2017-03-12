package cn.annpeter.graduation.project.base.restful.rpc.serialization;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

/**
 * Created on 2017/02/20
 *
 * @author annpeter.it@gmail.com
 */
public class CustomObjectMapper extends ObjectMapper {

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

package cn.annpeter.graduation.project.base.common.helper;

import cn.annpeter.graduation.project.base.common.util.JsonUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created on 2017/03/27
 *
 * @author annpeter.it@gmail.com
 */
public class JsonStringSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String title, JsonGenerator jGen, SerializerProvider provider)
            throws IOException {
        jGen.writeObject(JsonUtils.string2JsonNode(title));
    }
}

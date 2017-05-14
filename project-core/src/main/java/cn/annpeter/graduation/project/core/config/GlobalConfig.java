package cn.annpeter.graduation.project.core.config;

import cn.annpeter.graduation.project.core.config.bean.Email;
import cn.annpeter.graduation.project.core.config.bean.Redis;
import cn.annpeter.graduation.project.core.config.bean.Web;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.InputStream;
import java.util.Properties;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

/**
 * Created on 2017/03/30
 *
 * @author annpeter.it@gmail.com
 */
public class GlobalConfig {
    public static final Web web;
    public static final Redis redis;
    public static final Email email;
    public static final Properties datasource;

    static {
        ResourceLoader loader = new DefaultResourceLoader();
        try (InputStream inputStream = loader.getResource("context.yml").getInputStream()) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

            mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
            GlobalConfigDef configDef = mapper.readValue(inputStream, GlobalConfigDef.class);

            web = configDef.web;
            redis = configDef.redis;
            email = configDef.email;
            datasource = configDef.datasource;
        } catch (Exception e) {
            throw new Error("加载全局配置文件失败", e);
        }
    }

    private static class GlobalConfigDef {
        public Web web;
        public Redis redis;
        public Email email;
        public Properties datasource;
    }
}

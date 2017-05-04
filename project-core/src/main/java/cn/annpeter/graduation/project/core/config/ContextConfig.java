package cn.annpeter.graduation.project.core.config;

import freemarker.template.utility.XmlEscape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.AbstractMap.SimpleEntry;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created on 2017/03/16
 *
 * @author annpeter.it@gmail.com
 */
@Configuration
@ComponentScan(basePackages = {
        "cn.annpeter.graduation.project.core.service",
        "cn.annpeter.graduation.project.dal.dao"
})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ContextConfig {
    private static final Logger logger = LoggerFactory.getLogger(ContextConfig.class);

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        logger.info("==== freeMarkerConfigurer init ====");

        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();

        configurer.setTemplateLoaderPath("classpath:/freemarker/");
        configurer.setDefaultEncoding(StandardCharsets.UTF_8.name());
        configurer.setFreemarkerVariables(Stream.of(
                new SimpleEntry<>("xml_escape", XmlEscape.class)
        ).collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue)));

        Properties prop = new Properties();
        prop.put("classic_compatible", "true");
        configurer.setFreemarkerSettings(prop);
        return configurer;
    }

}

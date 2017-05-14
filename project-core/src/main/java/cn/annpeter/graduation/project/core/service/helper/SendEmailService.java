package cn.annpeter.graduation.project.core.service.helper;

import cn.annpeter.graduation.project.base.common.util.RetryUtils;
import cn.annpeter.graduation.project.core.util.SendEmailUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static cn.annpeter.graduation.project.core.config.GlobalConfig.email;


/**
 * Created on 2017/04/01
 *
 * @author annpeter.it@gmail.com
 */
@Service
public class SendEmailService implements InitializingBean {
    private static final String PROGRAM_TITLE = "xcard-service";

    private Configuration configuration;

    @Resource
    private FreeMarkerConfig freemarkerConfig;

    @Override
    public void afterPropertiesSet() throws Exception {
        configuration = freemarkerConfig.getConfiguration();
    }

    public void sendSystemErrorEmail(Map parameterMap, Throwable throwable) {
        RetryUtils.retryOrDiscard(() -> {
            Map<String, Object> param = new HashMap<>();
            param.put("parameterMap", parameterMap);
            param.put("throwable", ExceptionUtils.getFullStackTrace(throwable));
            param.put("program", PROGRAM_TITLE);

            Template tpl = configuration.getTemplate("SystemErrorEmail.ftl");

            String html = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, param);

            return SendEmailUtils.sendMail(email.systemErrorEmailReceiver, "系统异常报告", html);
        }, 3, 10, TimeUnit.SECONDS, true, "发送系统异常报告邮件失败");
    }

}

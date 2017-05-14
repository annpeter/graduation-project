package cn.annpeter.graduation.project.core.util;

import cn.annpeter.graduation.project.base.common.exception.CommonException;
import cn.annpeter.graduation.project.base.common.model.ResultCodeEnum;
import cn.annpeter.graduation.project.core.config.GlobalConfig;
import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created on 2016/10/10
 *
 * @author annpeter.it@gmail.com
 */
public class SendEmailUtils {

    private static final Logger logger = LoggerFactory.getLogger(SendEmailUtils.class);

    private static final String SMTP_HOST;
    private static final String SMTP_PORT;
    private static final String SMTP_TIMEOUT;
    private static final String SSL_ENABLE;
    private static final String SEND_MAIL_ACCOUNT;
    private static final String SEND_MAIL_PWD;

    static {
        SMTP_HOST = GlobalConfig.email.smtpHost;
        SMTP_PORT = GlobalConfig.email.smtpPort;
        SMTP_TIMEOUT = GlobalConfig.email.smtpTimeout;
        SSL_ENABLE = GlobalConfig.email.sslEnable;

        SEND_MAIL_ACCOUNT = GlobalConfig.email.sendMailAccount;
        SEND_MAIL_PWD = GlobalConfig.email.sendMailPwd;
    }

    // 默认发送者的信息
    public static boolean sendMail(String receiver, String subject, String content) {
        Map<String, Boolean> resultMap = sendMail(SEND_MAIL_ACCOUNT, SEND_MAIL_PWD, Stream.of(receiver).collect(Collectors.toList()), subject, content);
        Boolean sendSuccess = resultMap.get(receiver);
        return sendSuccess == null ? false : sendSuccess;
    }

    public static Map<String, Boolean> sendMail(List<String> receiverList, String subject, String content) {
        return sendMail(SEND_MAIL_ACCOUNT, SEND_MAIL_PWD, receiverList, subject, content);
    }

    public static Map<String, Boolean> sendMail(String from, String password, List<String> receiverList, String subject, String content) {
        Map<String, Boolean> sendResult = new HashMap<>();
        try {
            MailSSLSocketFactory sf = new MailSSLSocketFactory();

            sf.setTrustAllHosts(true);
            // 创建与邮件服务器连接
            Properties prop = new Properties();
            prop.put("mail.transport.protocol", "smtp");
            prop.put("mail.smtp.host", SMTP_HOST);
            prop.put("mail.smtp.port", SMTP_PORT);
            prop.put("mail.smtp.timeout", SMTP_TIMEOUT);
            prop.put("mail.smtp.ssl.enable", SSL_ENABLE);
            prop.put("mail.smtp.ssl.socketFactory", sf);
            prop.put("mail.smtp.auth", SSL_ENABLE);           // 连接验证
            prop.put("mail.debug", "true");   // 在控制台显示日志
            Session session = Session.getInstance(prop);

            // 编写邮件内容
            MimeMessage message = new MimeMessage(session);

            // from字段
            message.setFrom(new InternetAddress(from));
            message.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));

            // 内容组合实体
            MimeMultipart mimeMultipart = new MimeMultipart();

            // 给email添加内容
            MimeBodyPart bodyPart = new MimeBodyPart();

            // 身份校验
            bodyPart.setContent(content, "text/html;charset=utf-8");
            mimeMultipart.addBodyPart(bodyPart);
            mimeMultipart.setSubType("related");
            message.setContent(mimeMultipart);
            // 使用Transport发送邮件
            Transport transport = session.getTransport();
            // 发邮件前进行身份校验
            transport.connect(from, password);

            for (String receiver : receiverList) {
                try {
                    InternetAddress[] userAddressList = InternetAddress.parse(receiver);
                    message.setRecipients(Message.RecipientType.TO, userAddressList);
                    transport.sendMessage(message, message.getAllRecipients());

                    sendResult.put(receiver, true);
                    logger.info("发送邮件至--> {}, 主题 --> {} 成功.", receiver, subject);
                } catch (Exception ex) {
                    logger.info("发送邮件至 --> {}, 主题 --> {} 失败.", receiver, subject, ex);
                    sendResult.put(receiver, false);
                }
            }
            return sendResult;
        } catch (Exception ex) {
            throw new CommonException(ResultCodeEnum.UNKNOWN_ERROR, "发送邮件失败", "receiver=" + receiverList + ", subject=" + subject, ex);
        }
    }

}



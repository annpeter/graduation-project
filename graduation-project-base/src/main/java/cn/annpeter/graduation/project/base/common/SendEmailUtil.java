package cn.annpeter.graduation.project.base.common;

import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created on 2016/10/10
 *
 * @author annpeter.it@gmail.com
 */
public class SendEmailUtil {

    private static final Logger logger = LoggerFactory.getLogger(SendEmailUtil.class);

    private static final String SEND_ACCOUNT;

    private static final String SEND_PWD;

    private static final String MAIL_HOST;

    static {
        SEND_ACCOUNT = PropertyUtils.getProperty("sendmail.account");
        SEND_PWD = PropertyUtils.getProperty("sendmail.pwd");
        MAIL_HOST = PropertyUtils.getProperty("mail.smtp.host");
    }

    /**
     * 默认发送者的信息
     */
    public static boolean sendMail(String to, String subject, String emailContent) {
        String[] sendArray = new String[1];
        sendArray[0] = to;
        Map<String, Boolean> resultMap = sendMail(SEND_ACCOUNT, SEND_PWD, sendArray, subject, emailContent);
        Boolean sendSuccess = resultMap.get(to);
        return sendSuccess == null ? false : sendSuccess;
    }

    public static Map<String, Boolean> sendMail(String from, String password, String[] to, String subject,
                                                String emailContent) {

        Map<String, Boolean> sendResult = new HashMap<>();
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e1) {
            return sendResult;
        }

        sf.setTrustAllHosts(true);
        // 1创建与邮件服务器连接
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", MAIL_HOST);
        properties.put("mail.smtp.ssl.enable", "false");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        properties.put("mail.smtp.auth", "true");// 连接验证
        properties.put("mail.debug", "false");// 在控制台显示日志
        Session session = Session.getInstance(properties);
        // 2编写邮件内容
        MimeMessage message = new MimeMessage(session);
        // from字段
        try {
            message.setFrom(new InternetAddress(from));

            message.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));
        } catch (Exception e) {
            logger.error("", e);
            return sendResult;
        }

        //内容组合实体
        MimeMultipart mimeMultipart = new MimeMultipart();

        //给email添加内容
        MimeBodyPart content = new MimeBodyPart();
        Transport transport = null;

        try {
            content.setContent(emailContent, "text/html;charset=utf-8");
            mimeMultipart.addBodyPart(content);
            mimeMultipart.setSubType("related");
            message.setContent(mimeMultipart);
            // 3 使用Transport发送邮件
            transport = session.getTransport();
            // 发邮件前进行身份校验
            transport.connect(from, password);
        } catch (Exception e) {
            logger.error("", e);
            return sendResult;
        }

        for (String s : to) {
            try {
                InternetAddress[] userAddressList = new InternetAddress().parse(s);
                message.setRecipients(Message.RecipientType.TO, userAddressList);
                transport.sendMessage(message, message.getAllRecipients());
                sendResult.put(s, true);
                logger.info("send mail to --> {},subject-->{} success.", s, subject);
            } catch (Exception e) {
                sendResult.put(s, false);
            }
        }
        return sendResult;
    }

}



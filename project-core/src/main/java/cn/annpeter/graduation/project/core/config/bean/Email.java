package cn.annpeter.graduation.project.core.config.bean;

/**
 * Created on 2017/04/06
 *
 * @author annpeter.it@gmail.com
 */
public final class Email {
    public String smtpHost;
    public String smtpPort;
    public String smtpTimeout;
    public String sslEnable;
    public String sendMailAccount;
    public String sendMailPwd;

    public String systemErrorEmailReceiver;
    public Boolean sendSystemErrorEmail;
}
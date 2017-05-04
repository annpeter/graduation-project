package cn.annpeter.graduation.project.chat.protocol;

/**
 * Created on 2017/03/12
 *
 * @author annpeter.it@gmail.com
 */
public enum IMP {
    SYSTEM("SYSTEM"),   // 系统消息
    LOGIN("LOGIN"),     // 登录命令
    LOGOUT("LOGOUT"),   // 登出命令
    USER("USER"),       // 所有用户
    CHAT("CHAT");       // 聊天

    public static boolean isSupport(String msg) {
        return msg.matches("^\\[(SYSTEM|LOGIN|LOGOUT|CHAT)\\]");
    }

    private String name;

    IMP(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

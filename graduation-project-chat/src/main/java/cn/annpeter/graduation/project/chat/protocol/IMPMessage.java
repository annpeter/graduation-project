package cn.annpeter.graduation.project.chat.protocol;

import org.msgpack.annotation.Message;

import java.io.Serializable;

/**
 * 自定义协议消息内容
 * Created on 2017/03/12
 *
 * @author annpeter.it@gmail.com
 */
@Message
public class IMPMessage implements Serializable {
    private String addr;    // IP地址及端口
    private String cmd;     // 命令类型, 如: [LOGIN],[SYSTEM]等
    private long time;      // 命令发送时间
    private String sender;  // 命令发送人
    private String receiver;// 命令接收人
    private String content; // 消息内容

    public IMPMessage() {
    }

    public IMPMessage(String cmd, long time, String sender) {
        this.cmd = cmd;
        this.time = time;
        this.sender = sender;
    }

    public IMPMessage(String cmd, long time, String sender, String content) {
        this.cmd = cmd;
        this.time = time;
        this.sender = sender;
        this.content = content;
    }


    public String getAddr() {
        return addr;
    }

    public String getCmd() {
        return cmd;
    }

    public long getTime() {
        return time;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "IMPMessage{" +
                "addr='" + addr + '\'' +
                ", cmd='" + cmd + '\'' +
                ", time=" + time +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

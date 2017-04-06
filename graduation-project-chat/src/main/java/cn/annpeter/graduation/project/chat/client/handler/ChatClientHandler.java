package cn.annpeter.graduation.project.chat.client.handler;

import cn.annpeter.graduation.project.chat.protocol.IMP;
import cn.annpeter.graduation.project.chat.protocol.IMPMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * Created on 2017/03/14
 *
 * @author annpeter.it@gmail.com
 */
public class ChatClientHandler extends ChannelInboundHandlerAdapter {
    private String nickName;
    private ChannelHandlerContext ctx;

    public ChatClientHandler(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;

        // 首先登录
        IMPMessage msg = new IMPMessage(IMP.LOGIN.getName(), System.currentTimeMillis(), nickName);
        sendMsg(msg);
        System.out.println("成功连接至服务器，已执行登录动作");
        session();
    }

    private void session() {
        Thread thread = new Thread(() -> {
            System.out.println("你好, 请在控制台输入消息");
            Scanner sc = new Scanner(System.in);

            IMPMessage msg;
            do {
                String content = sc.nextLine();
                if ("exit".equals(content)) {
                    msg = new IMPMessage(IMP.LOGOUT.getName(), System.currentTimeMillis(), nickName);
                } else {
                    msg = new IMPMessage(IMP.CHAT.getName(), System.currentTimeMillis(), nickName, content);
                }
            } while (sendMsg(msg));
            sc.close();
        });

        thread.start();
    }

    // 往服务端发送消息
    private boolean sendMsg(IMPMessage msg) {
        ctx.channel().writeAndFlush(msg);
        System.out.println("消息已发送至服务器,请继续输入");
        return !StringUtils.equals(msg.getCmd(), IMP.LOGIN.getName());
    }
}

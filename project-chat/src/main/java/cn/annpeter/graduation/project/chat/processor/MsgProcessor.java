package cn.annpeter.graduation.project.chat.processor;

import cn.annpeter.graduation.project.chat.protocol.IMP;
import cn.annpeter.graduation.project.chat.protocol.IMPDecoder;
import cn.annpeter.graduation.project.chat.protocol.IMPEncoder;
import cn.annpeter.graduation.project.chat.protocol.IMPMessage;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 消息处理相关逻辑(消息分发)
 * Created on 2017/03/12
 *
 * @author annpeter.it@gmail.com
 */
public class MsgProcessor {

    private static final ChannelGroup onlineUsers = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static final AttributeKey<String> USER_NAME_KEY = AttributeKey.valueOf("userName");

    private IMPDecoder decoder = new IMPDecoder();
    private IMPEncoder encoder = new IMPEncoder();


    // 如果有用户登录, 把所有在线用户信息保存到一个统一的容器中
    public void login(Channel client) {

    }

    // 如果有用户退出, 就要从容器中将用户移除
    public void logout(Channel client) {
    }

    // 把消息分发到每个连接到服务器上的客户端
    public void sendMsg(Channel client, IMPMessage originMsg) {
        if (null == originMsg) {
            return;
        }

        // 分发逻辑
        if (IMP.LOGIN.getName().equals(originMsg.getCmd())) {
            onlineUsers.add(client);
            for (Channel channel : onlineUsers) {
                IMPMessage msg;
                if (channel != client) {
                    msg = new IMPMessage(IMP.SYSTEM.getName(), System.currentTimeMillis(), null, originMsg.getSender() + "已加入");
                } else {
                    msg = new IMPMessage(IMP.SYSTEM.getName(), System.currentTimeMillis(), null, "已与服务器建立连接");
                    client.attr(USER_NAME_KEY).set(originMsg.getSender());

                }
                channel.writeAndFlush(new TextWebSocketFrame(encoder.encode(msg)));
            }
        } else if (IMP.CHAT.getName().equals(originMsg.getCmd())) {
            for (Channel channel : onlineUsers) {
                if (channel != client) {
                    IMPMessage msg = new IMPMessage(IMP.CHAT.getName(), System.currentTimeMillis(), originMsg.getSender(), originMsg.getContent());
                    channel.writeAndFlush(new TextWebSocketFrame(encoder.encode(msg)));
                }
            }
        }
    }

    public void sendMsg(Channel client, String msg) {
        sendMsg(client, decoder.decode(msg));
    }

    public static ChannelGroup getOnlineUsers() {
        return onlineUsers;
    }
}

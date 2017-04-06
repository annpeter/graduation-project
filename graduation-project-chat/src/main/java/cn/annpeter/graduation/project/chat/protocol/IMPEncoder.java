package cn.annpeter.graduation.project.chat.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.apache.commons.lang3.StringUtils;
import org.msgpack.MessagePack;

/**
 * Created on 2017/03/12
 *
 * @author annpeter.it@gmail.com
 */
public class IMPEncoder extends MessageToByteEncoder<IMPMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, IMPMessage msg, ByteBuf out) throws Exception {
        // 序列化过程
        out.writeBytes(new MessagePack().write(msg));
    }

    public String encode(IMPMessage msg) {
        if (null != msg) {
            String cmd = msg.getCmd();

            String result = "[" + cmd + "][" + msg.getTime() + "]";

            if (StringUtils.equals(IMP.LOGIN.getName(), cmd) ||
                    StringUtils.equals(IMP.LOGOUT.getName(), cmd)) {
                result += "[" + msg.getSender() + "]";
            }
            if (StringUtils.isNotEmpty(msg.getContent())) {
                result += " - " + msg.getContent();
            }

            return result;
        }
        return "";
    }
}

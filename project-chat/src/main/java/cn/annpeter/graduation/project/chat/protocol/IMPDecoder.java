package cn.annpeter.graduation.project.chat.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.commons.lang3.StringUtils;
import org.msgpack.MessagePack;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2017/03/12
 *
 * @author annpeter.it@gmail.com
 */
public class IMPDecoder extends ByteToMessageDecoder {

    private static final Pattern pattern = Pattern.compile("^\\[(.*)\\](\\s\\-\\s(.*))?");

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        try {
            final int readableLen = in.readableBytes();
            final byte[] byteArr = new byte[readableLen];

            // 如果本解码器能解析(反序列化), 就把缓冲区的内容清空, 以免下面的解码器继续解码
            in.getBytes(in.readerIndex(), byteArr, 0, readableLen);
            out.add(new MessagePack().read(byteArr, IMPMessage.class));
            in.clear();
        } catch (Exception e) {
            // 告诉下面的其它解码器: 我解析不了, 你来解析吧
            ctx.channel().pipeline().remove(this);
        }
    }

    public IMPMessage decode(String msg) {
        if (StringUtils.isEmpty(msg)) {
            return null;
        }

        Matcher matcher = pattern.matcher(msg);
        String header = "";
        String content = "";
        if (matcher.matches()) {
            header = matcher.group(1);
            content = matcher.group(3);
        }
        String[] headers = header.split("\\]\\[");
        long time = Long.parseLong(headers[1]);
        String name = headers[2];
        String cmd = headers[0];


        if (StringUtils.equals(IMP.LOGIN.getName(), cmd) ||
                StringUtils.equals(IMP.LOGOUT.getName(), cmd)) {
            return new IMPMessage(cmd, time, name);
        } else if (StringUtils.equals(IMP.CHAT.getName(), cmd) ||
                StringUtils.equals(IMP.SYSTEM.getName(), cmd)) {
            return new IMPMessage(cmd, time, name, content);
        }

        return null;
    }
}

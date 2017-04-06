package cn.annpeter.graduation.project.chat.server.handler;

import cn.annpeter.graduation.project.chat.processor.MsgProcessor;
import cn.annpeter.graduation.project.chat.protocol.IMPMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created on 2017/03/13
 *
 * @author annpeter.it@gmail.com
 */
public class IMPHandler extends SimpleChannelInboundHandler<IMPMessage> {
    private MsgProcessor processor = new MsgProcessor();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IMPMessage msg) throws Exception {
        processor.sendMsg(ctx.channel(), msg);
    }
}

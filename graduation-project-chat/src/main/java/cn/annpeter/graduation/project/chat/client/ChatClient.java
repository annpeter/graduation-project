package cn.annpeter.graduation.project.chat.client;

import cn.annpeter.graduation.project.chat.client.handler.ChatClientHandler;
import cn.annpeter.graduation.project.chat.protocol.IMPDecoder;
import cn.annpeter.graduation.project.chat.protocol.IMPEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * Created on 2017/03/14
 *
 * @author annpeter.it@gmail.com
 */
public class ChatClient {
    private String host;
    private int port;

    private String nickName;

    public ChatClient(String host, int port, String nickName) {
        this.host = host;
        this.port = port;
        this.nickName = nickName;
    }

    public void connect() {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
                            pipeline.addLast(new IMPDecoder());
                            pipeline.addLast(new IMPEncoder());

                            pipeline.addLast(new ChatClientHandler(nickName));
                        }
                    });

            ChannelFuture future = bootstrap.connect(this.host, this.port).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}

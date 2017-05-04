package cn.annpeter.graduation.project.chat.server;

import cn.annpeter.graduation.project.chat.protocol.IMPDecoder;
import cn.annpeter.graduation.project.chat.protocol.IMPEncoder;
import cn.annpeter.graduation.project.chat.server.handler.HttpHandler;
import cn.annpeter.graduation.project.chat.server.handler.IMPHandler;
import cn.annpeter.graduation.project.chat.server.handler.WebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created on 2017/03/12
 *
 * @author annpeter.it@gmail.com
 */
public class ChatServer {
    private static final Logger logger = LoggerFactory.getLogger(ChatServer.class);

    private int port = 8080;

    public ChatServer(int port) {
        this.port = port;
    }

    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
                            // =========== 对自定义IMP协议的支持 ===========
                            pipeline.addLast(new IMPDecoder());
                            pipeline.addLast(new IMPEncoder());
                            pipeline.addLast(new IMPHandler());


                            // =========== 对HTTP的支持 ===========
                            pipeline.addLast(new HttpServerCodec());
                            // 将http请求或响应变成一个FullHttpRequest或FullHttpResponse对象
                            pipeline.addLast(new HttpObjectAggregator(64 * 1024));
                            // 用来处理文件流
                            pipeline.addLast(new ChunkedWriteHandler());
                            pipeline.addLast(new HttpHandler());


                            // =========== 对WebSocket的支持 ===========
                            pipeline.addLast(new WebSocketServerProtocolHandler("/im"));
                            pipeline.addLast(new WebSocketHandler());
                        }
                    });

            ChannelFuture future = bootstrap.bind(port).sync();
            logger.info("chat server start on port {}.", port);

            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

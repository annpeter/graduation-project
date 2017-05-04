package cn.annpeter.graduation.project.chat.server.handler;

import cn.annpeter.graduation.project.base.common.util.JsonUtils;
import cn.annpeter.graduation.project.chat.processor.MsgProcessor;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.stream.Collectors;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.ssl.ApplicationProtocolNames.HTTP_1_1;


/**
 * Created on 2017/03/12
 *
 * @author annpeter.it@gmail.com
 */
public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private URL basePath = HttpHandler.class.getClassLoader().getResource("");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {

        String uri = request.uri();

        String resource = uri.equals("/") ? "index.html" : uri;

        try {
            if (resource.equals("/chatUser")) {

                String userList = JsonUtils.object2String(MsgProcessor.getOnlineUsers().parallelStream().map(item -> item.attr(MsgProcessor.USER_NAME_KEY)).collect(Collectors.toList()));
                FullHttpResponse response = new DefaultFullHttpResponse(request.protocolVersion(), HttpResponseStatus.OK, Unpooled.wrappedBuffer(userList.getBytes("UTF-8")));
                boolean isKeepAlive = HttpUtil.isKeepAlive(request);
                if (isKeepAlive) {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                }

                response.headers().set(CONTENT_LENGTH, userList.length());

                ctx.channel().writeAndFlush(response);
            } else {
                RandomAccessFile file = new RandomAccessFile(getResource(resource), "r");

                HttpResponse response = new DefaultHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);
                boolean isKeepAlive = HttpUtil.isKeepAlive(request);
                if (isKeepAlive) {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                }

                if (uri.endsWith(".css")) {
                    response.headers().add(CONTENT_TYPE, "text/css");
                } else if (uri.endsWith(".js")) {
                    response.headers().add(CONTENT_TYPE, "text/javascript");
                } else if (uri.matches(".(png|jpg|gif|ico)")) {
                    response.headers().add(CONTENT_TYPE, "img/" + uri.substring(uri.lastIndexOf(".")));
                } else {
                    response.headers().add(CONTENT_TYPE, "text/html");
                }
                response.headers().set(CONTENT_LENGTH, file.length());

                ctx.write(response);
                ctx.write(new ChunkedNioFile(file.getChannel()));


                ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
                // 如果不是长连接, 文件全部输出后, 关闭连接
                if (!isKeepAlive) {
                    future.addListener(ChannelFutureListener.CLOSE);
                }
            }
        } catch (Exception e) {
            // 继续下一次请求, 服务端不会报错
            ctx.fireChannelRead(request.retain());
            return;
        }
    }

    private String getResource(String uri) throws URISyntaxException {
        String path = basePath.getPath() + uri;
        path.replaceAll("//", "/");
        return path;
    }
}

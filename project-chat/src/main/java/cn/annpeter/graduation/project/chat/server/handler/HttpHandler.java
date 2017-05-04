package cn.annpeter.graduation.project.chat.server.handler;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;


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

        RandomAccessFile file;
        try{
            file =  new RandomAccessFile(getResource(resource), "r");
        }catch (Exception e){
            // 继续下一次请求, 服务端不会报错
            ctx.fireChannelRead(request.retain());
            return;
        }

        HttpResponse response = new DefaultHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);
        if(uri.endsWith(".css")){
            response.headers().add(CONTENT_TYPE, "text/css");
        }else if(uri.endsWith(".js")){
            response.headers().add(CONTENT_TYPE, "text/javascript");
        }else if(uri.matches(".(png|jpg|gif|ico)")){
            response.headers().add(CONTENT_TYPE, "img/"+uri.substring(uri.lastIndexOf(".")));
        }else {
            response.headers().add(CONTENT_TYPE, "text/html");
        }

        boolean isKeepAlive = HttpUtil.isKeepAlive(request);
        if (isKeepAlive) {
            response.headers().set(CONTENT_LENGTH, file.length());
            response.headers().set(CONNECTION, KEEP_ALIVE);
        }

        ctx.write(response);
        ctx.write(new ChunkedNioFile(file.getChannel()));

        ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
        // 如果不是长连接, 文件全部输出后, 关闭连接
        if (!isKeepAlive) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    private String getResource(String uri) throws URISyntaxException {
        String path = basePath.getPath() + uri;
        path.replaceAll("//", "/");
        return path;
    }
}

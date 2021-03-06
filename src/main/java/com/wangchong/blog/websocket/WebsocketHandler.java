package com.wangchong.blog.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebsocketHandler extends SimpleChannelInboundHandler<Object> {

    Logger log = LoggerFactory.getLogger(WebsocketHandler.class);

    private WebSocketServerHandshaker handshaker;
    private static final String WEB_SOCKET_URL = "ws://localhost:8888/websocket";


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 客户端与服务端创建连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.add(ctx.channel());
        createSession(ctx.channel());
        log.info("当前用户数量--"+NettyConfig.group.size());
    }

    private void createSession(Channel channel) {
        UserSession session = new UserSession();
        session.setChannelId(channel.id().toString());

    }

    /**
     * 断开连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.remove(ctx.channel());
        ctx.channel().close();
        log.info("当前用户数量--"+NettyConfig.group.size());
    }

    /**
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * @param channelHandlerContext
     * @param o
     * @throws Exception
     */
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        if (o instanceof FullHttpRequest) { //处理客户端向服务端发起的http请求

            handHttpRequest(channelHandlerContext, (FullHttpRequest) o);

        } else if (o instanceof WebSocketFrame) { //处理websocket请求
            handWebsocketFrame(channelHandlerContext, (WebSocketFrame) o);

        }
    }

    /**
     * 处理客户端想服务端发起的http握手请求的业务
     *
     * @param ctx
     * @param req
     */
    private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        if (!req.getDecoderResult().isSuccess() || !"websocket".equals(req.headers().get("Upgrade"))) {
            sendHttpResponse(ctx,req,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        String uri = req.getUri();
        log.info("uri---------------"+uri);
        WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL,null,false);
        handshaker = factory.newHandshaker(req);
        if(handshaker == null){
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        }else{
            handshaker.handshake(ctx.channel(),req);
        }

    }


    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        if (res.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        ChannelFuture future = ctx.channel().write(res);
        if (res.getStatus().code() != 200) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }


    private void handWebsocketFrame(ChannelHandlerContext ctx,WebSocketFrame frame){
        if(frame instanceof CloseWebSocketFrame){
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
        }

        if(frame instanceof PingWebSocketFrame){
            ctx.write(new PongWebSocketFrame(frame.content().retain()));
        }

        if((frame instanceof TextWebSocketFrame)){
            //服务端接收到的消息
            String request = ((TextWebSocketFrame) frame).text();
            if ("close".equals(request)) {
                ctx.channel().close();
                return;
            }

            // 应答消息
            TextWebSocketFrame resFrame = new TextWebSocketFrame(ctx.channel().id()+"----ddddd--"+request);
            //群发消息
            ctx.channel().writeAndFlush(resFrame);
        } else{
            throw new RuntimeException("不支持该消息类型");
        }





    }
}

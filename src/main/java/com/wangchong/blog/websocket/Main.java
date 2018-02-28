package com.wangchong.blog.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Main {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup,workGroup);
            server.channel(NioServerSocketChannel.class);
            server.childHandler(new WebsocketChannelHandle());
            System.out.println("服务端开启，等待客户端连接");
            Channel channel = server.bind(8888).sync().channel();
            channel.closeFuture().sync();

        }catch (Exception e){
           e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}

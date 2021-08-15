package com.york.thread.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;

/**
 * @author yangjianzhong
 * @description
 * @date 2021/8/13 10:25 下午
 **/
public class EchoServer {

    public static void main(String[] args) throws InterruptedException {
        ServerHandler serverHandler = new ServerHandler();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        HttpRequestDecoder requestDecoder = new HttpRequestDecoder();
        serverBootstrap.group(eventLoopGroup).channel(NioServerSocketChannel.class)
                .localAddress(8001)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline p = socketChannel.pipeline();
                        p.addLast(new HttpRequestDecoder());
                        p.addLast(new HttpObjectAggregator(1024*1024));
                        p.addLast(new HttpServerExpectContinueHandler());
                        p.addLast(new HttpResponseEncoder());
                        p.addLast(new HttpHelloWorldServerHandler());
                    }
                });
        ChannelFuture f = serverBootstrap.bind().sync();
        f.channel().closeFuture().sync();
        eventLoopGroup.shutdownGracefully().sync();
    }
}

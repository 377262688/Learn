//package com.york.thread.netty;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//
//import java.net.InetSocketAddress;
//
///**
// * @author york
// * @create 2020-03-27 19:56
// **/
//public class EchoClient {
//
//    private static final String host = "127.0.0.1";
//
//    private static final int port = 9002;
//
//    public static void main(String[] args) throws InterruptedException {
//        start();
//    }
//
//    public static void start() throws InterruptedException {
//        EventLoopGroup group = new NioEventLoopGroup();
//        try {
//            Bootstrap b = new Bootstrap();
//            b.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host,port)).handler(new ChannelInitializer<SocketChannel>() {
//                @Override
//                protected void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new EchoClientHandler());
//                }
//            });
//            ChannelFuture f = b.connect().sync();
//            f.channel().closeFuture().sync();
//        } finally {
//            group.shutdownGracefully();
//        }
//    }
//}

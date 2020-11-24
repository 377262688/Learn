package com.york.thread.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author york
 * @create 2020-03-27 16:56
 **/
public class ChannelHandlerDemo {

    public static void main(String[] args) {
        Channel channel = null;
        ChannelFuture channelFuture = channel.connect(new InetSocketAddress("127.0.0.1",9001));
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    ByteBuf byteBuf = Unpooled.copiedBuffer("Hello", Charset.defaultCharset());
                    ChannelFuture wf = channelFuture.channel().writeAndFlush(byteBuf);
                } else {
                    Throwable throwable = channelFuture.cause();
                    throwable.printStackTrace();
                }
            }
        });
    }
}

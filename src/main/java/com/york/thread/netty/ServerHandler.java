package com.york.thread.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangjianzhong
 * @description
 * @date 2021/8/13 10:26 下午
 **/
@Slf4j
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {


    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("收到消息:\n\n" + byteBuf.toString(CharsetUtil.UTF_8));
        ctx.write(byteBuf);
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.write("处理完成");
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
        ctx.close();
    }
}

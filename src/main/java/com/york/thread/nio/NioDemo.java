package com.york.thread.nio;

import org.apache.ibatis.annotations.SelectKey;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author yangjianzhong
 * @description
 * @date 2021/8/12 6:22 下午
 **/
public class NioDemo {

    private static Selector selector;
    public static void main(String[] args) throws IOException {
        selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        SocketAddress socketAddress = new InetSocketAddress(8001);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(socketAddress);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();
                if (key.isAcceptable()) {
                    doAccept(key);
                } else if (key.isReadable()) {
                    doRead(key);
                }
            }
        }
    }

    private static void doRead(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(4048);
        StringBuffer sb = new StringBuffer();
        int readLen = socketChannel.read(byteBuffer);
        if (readLen < 0) {
            socketChannel.close();
        }
        sb.append(byteBuffer.getChar());
        byteBuffer.clear();
        byteBuffer.flip();
        byteBuffer.put("123".getBytes(StandardCharsets.UTF_8));
        socketChannel.register(selector, SelectionKey.OP_WRITE);
        socketChannel.write(byteBuffer);
    }

    private static void doAccept(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }
}

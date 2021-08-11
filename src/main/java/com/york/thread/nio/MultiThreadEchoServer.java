package com.york.thread.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by york on 2019/4/9.
 */
@Slf4j
public class MultiThreadEchoServer {

    private static Selector selector;
    private static final ExecutorService tp = Executors.newFixedThreadPool(100);
    public static Map<Socket,Long> time_stat = new HashMap<>(10240);

    private static void startServer() throws IOException {
        selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        InetSocketAddress isa = new InetSocketAddress(8000);
        ssc.socket().bind(isa);

        SelectionKey acceptKey = ssc.register(selector,SelectionKey.OP_ACCEPT);

        for (;;) {
            selector.select();
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> i = readyKeys.iterator();
            while (i.hasNext()) {
                SelectionKey sk = i.next();
                i.remove();

                if (sk.isAcceptable()) {
                    doAccept(sk);
                } else if (sk.isValid() && sk.isReadable()) {
                    if (!time_stat.containsKey(((SocketChannel)sk.channel()).socket())) {
                        time_stat.put((((SocketChannel) sk.channel()).socket()),System.currentTimeMillis());
                        doRead(sk);
                    }
                } else if (sk.isValid() && sk.isWritable()) {
//                    doWrite(sk);
                    long e = System.currentTimeMillis();
                    long s = time_stat.remove(((SocketChannel)sk.channel()).socket());
                    log.info("spend time:" + (e - s) + "ms");
                }

            }
        }
    }

    private static void doAccept(SelectionKey sk) {
        ServerSocketChannel server = (ServerSocketChannel) sk.channel();
        SocketChannel clientChannel;
        try {
            clientChannel = server.accept();
            clientChannel.configureBlocking(false);

            SelectionKey clientKey = clientChannel.register(selector,SelectionKey.OP_READ);
            EchoClient echoClient = new EchoClient();
            clientKey.attach(echoClient);

            InetAddress address = clientChannel.socket().getInetAddress();
            log.info("accept connect from " + address.getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doRead(SelectionKey sk) {
        SocketChannel channel = (SocketChannel) sk.channel();
        ByteBuffer bb = ByteBuffer.allocate(8192);
        int len;
        StringBuilder sb = new StringBuilder();
        try {
            len = channel.read(bb);
            if (len < 0) {
                // TODO: 2019/4/9 disconnect
                return;
            }
        } catch (Exception e) {
            log.error("Faild to read from client",e);
            // TODO: 2019/4/9 disconnect
            return;
        }
        bb.flip();
        tp.execute(new HandleMsg1(sk,bb));
    }

    static class HandleMsg1 implements Runnable {
        SelectionKey sk;
        ByteBuffer bb;
        public HandleMsg1(SelectionKey sk,ByteBuffer bb) {
            this.sk = sk;
            this.bb = bb;
        }
        @Override
        public void run() {
            EchoClient echoClient = (EchoClient) sk.attachment();
            echoClient.enqueue(bb);
            sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            selector.wakeup();
        }
    }


    static class HandleMsg implements Runnable {
        Socket clientSocket;

        public HandleMsg(Socket socket) {
            clientSocket = socket;
        }

        @Override
        public void run() {
            log.info("开始执行");
            BufferedReader br = null;
            PrintWriter pw = null;
            try {
                br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                pw = new PrintWriter(clientSocket.getOutputStream(), true);
                String inputLine = null;
                long b = System.currentTimeMillis();
                log.info("开始执行");
                while ((inputLine = br.readLine()) != null) {
                    log.info(inputLine);
                    pw.println(inputLine);
                }
                long e = System.currentTimeMillis();
                log.info("spend time:" + (e - b) + "ms");
            } catch (IOException e) {
                log.error("io错误", e);
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    if (pw != null) pw.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        ServerSocket echoServer = null;
        tp.execute(() -> {
            try {
                startServer();
                log.error("启动server成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Thread.sleep(1000);
//        try {
//            echoServer = new ServerSocket(8000);
//        } catch (IOException e) {
//            log.error("",e);
//        }
        for (int i = 0;i < 10; i++) {
            try {
                Socket clientSocket = new Socket();
                clientSocket.connect(new InetSocketAddress("127.0.0.1",8000));
                log.info(clientSocket.getRemoteSocketAddress() + "connected");
                tp.execute(new HandleMsg(clientSocket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

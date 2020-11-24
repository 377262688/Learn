package com.york.thread.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author york
 * @create 2020-04-07 15:00
 **/
public class BIOPerConnectionPerThread {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8000));

        while (!Thread.currentThread().isInterrupted()) {
            Socket socket = serverSocket.accept();
            executorService.submit(new ConnectHandler(socket));
        }
    }

    private static class ConnectHandler implements Runnable {
        Socket socket;
        public ConnectHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted() && !socket.isClosed()) {
                try {
                    InputStream someThing = socket.getInputStream();
                    // 写数据
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
                    printWriter.write("xsada");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

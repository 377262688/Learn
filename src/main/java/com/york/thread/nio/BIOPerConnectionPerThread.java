package com.york.thread.nio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Objects;
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
        System.out.printf("启动成功");
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
                    InputStream inputStream = socket.getInputStream();
                    System.out.println("收到请求");
                    HttpCodec httpCodec = new HttpCodec();
                    // 解析响应行
                    String responseLine = httpCodec.readLine(inputStream);
                    System.out.println("请求行：" + responseLine);
                    System.out.println("请求头：");
                    // 解析响应头
                    Map<String, String> headers = httpCodec.readHeaders(inputStream);
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        System.out.println(entry.getKey() + ":" + entry.getValue());
                    }
                    // 解析 Content-Length 响应体
                    if (headers.containsKey("Content-Length")) {
                        int length = Integer.valueOf(headers.get("Content-Length"));
                        byte[] bytes = httpCodec.readBytes(inputStream, length);
                        System.out.println("\n请求体：" + new String(bytes));
                    } else {  // 分块编码
                        String response = httpCodec.readChunked(inputStream);
                        System.out.println("\n分块请求体：" + response);
                    }
                    // 写数据
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
                    String res = "HTTP/1.1 200 OK\n\n" +
                            "Server: Tengine\n" +
                            "Date: Tue, 10 Aug 2021 07:05:17 GMT\n" +
                            "Content-Type: application/json;charset=UTF-8\n" +
                            "Content-Length: 327\n" +
                            "Connection: keep-alive\n" +
                            "Keep-Alive: timeout=60\n" +
                            "vary: Origin,Access-Control-Request-Method,Access-Control-Request-Headers\n" +
                            "access-control-allow-origin: *\n" +
                            "x-envoy-upstream-service-time: 87\n" +
                            "x-b3-sampled: 1\n" +
                            "x-b3-traceid: f3f4c5d62cc2598d\n" +
                            "x-b3-spanid: 83b956e666d4132b\n" +
                            "x-b3-level: 1000\n\n" +
                            "{\n" +
                            "\t\"code\":200,\n" +
                            "\t\"data\":[\n" +
                            "\t\t{\n" +
                            "\t\t\t\"id\":921634,\n" +
                            "\t\t\t\"name\":\"体验官1\",\n" +
                            "\t\t\t\"nickName\":\"135****9908\",\n" +
                            "\t\t\t\"phone\":\"135****9908\",\n" +
                            "\t\t\t\"phoneCode\":\"323849\",\n" +
                            "\t\t\t\"phoneMask\":\"135****9908\",\n" +
                            "\t\t\t\"primaryType\":6,\n" +
                            "\t\t\t\"primaryTypeName\":\"KOC\",\n" +
                            "\t\t\t\"subTypeId\":32,\n" +
                            "\t\t\t\"subTypeName\":\"甄选官\"\n" +
                            "\t\t}\n" +
                            "\t],\n" +
                            "\t\"message\":\"操作成功\",\n" +
                            "\t\"success\":true\n" +
                            "}";
                    printWriter.write(res);
                    printWriter.flush();
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

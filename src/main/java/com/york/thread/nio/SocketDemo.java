package com.york.thread.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author york
 * @create 2020-03-27 16:32
 **/
public class SocketDemo {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9001);
        Socket clientSocket = serverSocket.accept();
        System.out.println(clientSocket.getLocalPort() + ":" + clientSocket.getRemoteSocketAddress() + ":" + clientSocket.getPort());
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(),true);
        String request,response;
        while ((request = in.readLine()) != null) {
            if ("Done".equals(request)) {
                break;
            }
            response = "response :" + request;
            printWriter.println(response);
        }


    }
}

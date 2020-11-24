package com.york.thread.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by york on 2019/4/9.
 */
@Slf4j
public class SocketClient {

    public static void main(String[] args) {
        Socket client = null;
        BufferedReader bf = null;
        PrintWriter pw = null;
        try {
            client = new Socket();
            client.connect(new InetSocketAddress("127.0.0.1",9001));
            pw = new PrintWriter(client.getOutputStream(),true);
            pw.println("Hello !");
            pw.flush();
            bf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            log.info("from server:" + bf.readLine());
            pw.println("hello1 !");
            pw.flush();
            bf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            log.info("from server:" + bf.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

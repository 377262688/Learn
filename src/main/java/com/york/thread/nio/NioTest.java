package com.york.thread.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author york
 * @create 2019-12-07 19:44
 **/
public class NioTest {

    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("nio-data.txt","rw");
        FileChannel channel = file.getChannel();
        //create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(12);
        int bytesRead = channel.read(buf); //read into buffer.
        while (bytesRead != -1) {
            buf.flip();  //make buffer ready for read
            while(buf.hasRemaining()){
                System.out.print((char) buf.get()); // read 1 byte at a time
            }
            buf.clear(); //make buffer ready for writing
            bytesRead = channel.read(buf);
        }
        file.close();
    }
}

package com.shepherd.common.channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/3/13 22:39
 */
public class FileChannelReadDemo {
    // FileChannel读取数据到buffer中
    public static void main(String[] args) throws Exception {
        //创建FileChannel
        RandomAccessFile aFile = new RandomAccessFile("/Users/shepherdmy/Desktop/nio/test1.txt","rw");
        FileChannel channel = aFile.getChannel();

        //创建Buffer
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //读取数据到buffer中
        int bytesRead = channel.read(buf);
        while(bytesRead != -1) {
            System.out.println("读取了："+bytesRead);
            buf.flip();
            while(buf.hasRemaining()) {
                System.out.println((char)buf.get());
            }
            buf.clear();
            bytesRead = channel.read(buf);
        }
        aFile.close();
        System.out.println("  结束了");
    }
}

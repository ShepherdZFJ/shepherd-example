package com.shepherd.example.nio.channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/3/13 23:08
 */
public class FileChannelWriteDemo {
    public static void main(String[] args) throws Exception {
        // FileChannel写数据，如果写入文件已经有数据，会被覆盖调
        RandomAccessFile aFile = new RandomAccessFile("/Users/shepherdmy/Desktop/nio/test2.txt","rw");
        FileChannel channel = aFile.getChannel();

        //创建buffer对象
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        String newData = "fileChannel write data: hello world, shepherd";
        buffer.clear();

        //写入内容
        buffer.put(newData.getBytes());

        buffer.flip();

        //FileChannel完成最终实现
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }

        //关闭
        channel.close();
    }
}

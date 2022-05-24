package com.shepherd.common.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/3/20 23:39
 */
public class FileChannelDemo {
    public static void main(String[] args) throws IOException {
        // 创建两个fileChannel
        RandomAccessFile aFile = new RandomAccessFile("/Users/shepherdmy/Desktop/nio/test1.txt","rw");
        FileChannel fromChannel = aFile.getChannel();

        RandomAccessFile bFile = new RandomAccessFile("/Users/shepherdmy/Desktop/nio/test1_1.txt","rw");
        FileChannel toChannel = bFile.getChannel();

        //fromChannel 传输到 toChannel
        long position = 0;
        long size = fromChannel.size();

        // 从给定的可读字节通道将字节传输到该通道的文件中, 谁调给谁
        toChannel.transferFrom(fromChannel,position,size);
        // 将该通道文件的字节传输到给定的可写字节通道 与上面相反
        // fromChannel.transferTo(0,size,toChannel);


        aFile.close();
        bFile.close();
        System.out.println("over!");
    }

}

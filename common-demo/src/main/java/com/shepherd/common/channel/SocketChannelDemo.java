package com.shepherd.common.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/3/20 23:53
 */
public class SocketChannelDemo {
    public static void main(String[] args) throws Exception {
        //创建SocketChannel
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));

//        SocketChannel socketChanne2 = SocketChannel.open();
//        socketChanne2.connect(new InetSocketAddress("www.baidu.com", 80));

        //设置阻塞和非阻塞
        socketChannel.configureBlocking(false);

        //读操作
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        socketChannel.read(byteBuffer);
        socketChannel.close();
        System.out.println("read over");

    }
}

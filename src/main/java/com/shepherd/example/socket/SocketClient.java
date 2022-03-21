package com.shepherd.example.socket;

import java.io.*;
import java.net.Socket;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/3/21 11:21
 */
public class SocketClient {

    // 若先执行SocketClient会提示无法连接到服务器，因为此时没有服务在监听8088端口
    public static void main(String[] args) throws InterruptedException {
        try {
            // 和服务器创建连接
            Socket socket = new Socket("localhost",8088);

            // 要发送给服务器的信息
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("客户端发送信息");
            pw.flush();

            socket.shutdownOutput();

            // 从服务器接收的信息
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info = null;
            while((info = br.readLine())!=null){
                System.out.println("我是客户端，服务器返回信息："+info);
            }

            br.close();
            is.close();
            os.close();
            pw.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

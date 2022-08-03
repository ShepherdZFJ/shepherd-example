package com.shepherd.common.bio;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/3/20 00:48
 */
public class BioDemo {
    public static void main(String[] args) throws IOException {
//        String source = "/Users/shepherdmy/baiduYunDownload/72-Elasticsearch核心技术与实战/13丨通过Analyzer进行分词.mp4";
//        String destination = "/Users/shepherdmy/Desktop/bio/bak/es.mp4";
//        long start = System.currentTimeMillis();
//        copyWithFileChannel(source, destination);
//        long end = System.currentTimeMillis();
//        System.out.println(end-start);

        ////    String word = "hahahahhahahahhah";
////    FileOutputStream fileOutputStream = null;
////    File file = new File("/Users/shepherdmy/Desktop/test_voice.txt");
////    if(!file.exists()){
////      file.createNewFile();
////    }
////    fileOutputStream = new FileOutputStream(file);
////    fileOutputStream.write(s.getBytes("gbk"));
////    fileOutputStream.flush();
////    fileOutputStream.close();
//
//    String word = "试试wefwf wew23423456电饭锅";
//    String path = "/Users/shepherdmy/Desktop/test_voice.txt";
//    BufferedWriter out = new BufferedWriter(
//            new OutputStreamWriter(new FileOutputStream(path,true)));
//    out.write(word+"\r\n");
//    out.close();
//    System.out.println(s);
        File file = new File("test_voice.txt");
        FileUtils.write(file, "我们we are family\r\n", true);
        FileUtils.write(file, "er二二二二2222\r\n", true);
        readFile();
        System.out.println(111);
    }

    public static void copyWithFileInputStream(String source, String destination) throws IOException {
        // 创建输入流，读取文件内容
        InputStream inputStream = new FileInputStream(source);
        OutputStream outputStream = new FileOutputStream(destination);
        byte[] buf = new byte[8192];
        int len;
        while ((len = inputStream.read(buf)) > 0) {
            outputStream.write(buf, 0, len);
        }
        inputStream.close();
        outputStream.close();
    }

    public static void copyWithBufferInputStream(String source, String destination) throws IOException {

        InputStream in = new FileInputStream(source);
        OutputStream out = new FileOutputStream(destination);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
        // bufferedInputStream默认缓冲区大小为8192
        // BufferedInputStream比FileInputStream多了一个缓冲区，执行read时先从缓冲区读取，当缓冲区数据读完时再把缓冲区填满。
        // 因此，当每次读取的数据量很小时，FileInputStream每次都是从硬盘读入，而BufferedInputStream大部分是从缓冲区读入。读取内存速度比
        // 读取硬盘速度快得多，因此BufferedInputStream效率高。
        // BufferedInputStream的默认缓冲区大小是8192字节。当每次读取数据量接近或远超这个值时，两者效率就没有明显差别了。
        byte[] buf = new byte[8192];
        int len;
        while ((len = bufferedInputStream.read(buf)) > 0) {
            bufferedOutputStream.write(buf, 0, len);
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }

    public static void copyWithFileChannel(String source, String destination) throws IOException {
        // 打开文件输入流
        FileChannel inChannel = new FileInputStream(source).getChannel();
        // 打开文件输出流
        FileChannel outChannel = new FileOutputStream(destination).getChannel();
        // 分配 1024 个字节大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(8192);
        // 将数据从通道读入缓冲区
        while (inChannel.read(buf) != -1) {
            // 切换缓冲区的读写模式
            buf.flip();
            // 将缓冲区的数据通过通道写到目的地
            outChannel.write(buf);
            // 清空缓冲区，准备下一次读
            buf.clear();
        }
        inChannel.close();
        outChannel.close();

    }

    public static void copyWithUtils(String source, String destination) throws IOException {
        FileUtils.copyFile(new File(source), new File(destination));
    }


    public static void readFile() {
        String pathname = "test_voice.txt"; // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
        //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
        //不关闭文件会导致资源的泄露，读写文件都同理
        //Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            //网友推荐更加简洁的写法
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

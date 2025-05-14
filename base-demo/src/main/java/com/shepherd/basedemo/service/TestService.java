package com.shepherd.basedemo.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.shepherd.basedemo.file.OssProperties;
import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.io.outputstream.ZipOutputStream;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionMethod;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/11/11 10:02
 */
@Service
@Slf4j
public class TestService {
    @Resource
    private OSSClient ossClient;
    @Resource
    private OssProperties ossProperties;


    public void downloadFile(List<String> list, HttpServletResponse response) throws IOException {
        byte[] buff = new byte[1024];
        int readLen;
        // 设置压缩流：直接写入response
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setCompressionMethod(CompressionMethod.DEFLATE);
        log.info("开始下载时间：" + System.currentTimeMillis());
        try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()))) {
            for (int i = 0; i < list.size(); i++) {
                String filename = list.get(i);
                zipParameters.setFileNameInZip(filename);
                zos.putNextEntry(zipParameters);
                log.info("oss时间：" + System.currentTimeMillis());
                try (InputStream inputStream = downloadOSSFile(filename)) {
                    log.info("读取时间：" + System.currentTimeMillis());
                    while ((readLen = inputStream.read(buff)) != -1) {
                        zos.write(buff, 0, readLen);
                    }
                }
                zos.closeEntry();
                log.info("下载第{}个文件成功: 文件名：{}，时间：{}", (i + 1), filename,  System.currentTimeMillis());
            }
        }
    }


    public InputStream downloadOSSFile(String fileName) {
        OSSObject ossObject = ossClient.getObject(ossProperties.getBucketName(), fileName);
        InputStream content = ossObject.getObjectContent();
        return content;
    }
}

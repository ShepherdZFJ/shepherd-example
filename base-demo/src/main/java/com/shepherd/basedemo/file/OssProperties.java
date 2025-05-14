package com.shepherd.basedemo.file;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/11/11 10:10
 */
@Configuration
@ConfigurationProperties(prefix = "aliyun-oss")
@Component()
@Data
public class OssProperties {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private Integer maxUploadSize;
    private Integer maxInMemorySize;

    @Bean
    public OSS ossClient(){
        OSS ossClient  = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        return ossClient;
    }
}


package com.wyhcode.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author weiyuhui
 * @date 2023/8/8 11:33
 * @description
 */
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioConfig implements InitializingBean {

    private String bucket;

    private String host;

    private String url;

    private String accessKey;

    private String secretKey;

    private MinioClient minioClient;
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(url,"Minio url 为空");
        Assert.hasText(accessKey,"Minio accessKey 为空");
        Assert.hasText(secretKey,"Minio secretKey 为空");
        this.minioClient = new MinioClient(this.host, this.accessKey, this.secretKey);
    }
}

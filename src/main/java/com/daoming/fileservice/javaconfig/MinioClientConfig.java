package com.daoming.fileservice.javaconfig;

import com.daoming.fileservice.common.PropertiesUtil;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.daoming.fileservice")
public class MinioClientConfig {

    @Autowired
    MinioServerConfig minioServerConfig;

    @Bean(name="minioClient")
    public MinioClient getMinioClient() throws MinioException {
        return new MinioClient(minioServerConfig.getEndpoint(),
                Integer.parseInt(minioServerConfig.getPort()),
                minioServerConfig.getAccessKey(),
                minioServerConfig.getSecretKey());
    }
}

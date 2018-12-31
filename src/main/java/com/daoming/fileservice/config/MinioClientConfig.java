package com.daoming.fileservice.config;

import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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

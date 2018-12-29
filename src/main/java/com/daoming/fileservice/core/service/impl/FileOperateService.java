package com.daoming.fileservice.core.service.impl;

import com.daoming.fileservice.core.service.IFileOperateService;
import io.minio.MinioClient;
import io.minio.messages.Bucket;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileOperateService implements IFileOperateService {

    @Autowired
    MinioClient minioClient;

    @Override
    public void uploadFile(String bucketName, String filePath, byte[] byteArray) {
        try {
            if (null == filePath || "" == filePath) return;
            // 调用statObject()来判断对象是否存在。
            // 如果不存在, statObject()抛出异常,
            // 否则则代表对象存在。
            InputStream stream = new ByteArrayInputStream(byteArray);
            minioClient.putObject(bucketName, filePath, stream, stream.available(), "application/octet-stream");
            System.out.println("file is uploaded successfully");
            stream.close();
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        } finally {
        }
    }

    @Override
    public byte[] downloadFile(String bucketName, String filePath) {
        try {
            InputStream stream = minioClient.getObject(bucketName, filePath);
            byte[] byteArray = IOUtils.toByteArray(stream);
            return byteArray;
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
            return null;
        }
    }
}

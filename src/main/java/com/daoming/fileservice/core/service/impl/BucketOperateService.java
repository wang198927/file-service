package com.daoming.fileservice.core.service.impl;

import com.daoming.fileservice.core.service.IBucketOperateService;
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
public class BucketOperateService implements IBucketOperateService {

    @Autowired
    MinioClient minioClient;

    @Override
    public List<String> listBucketName() {
        try {
            // 列出所有存储桶
            List<Bucket> bucketList = minioClient.listBuckets();
            List<String> bucketNameList = new ArrayList<>();
            for (Bucket bucket : bucketList) {
                System.out.println(bucket.creationDate() + ", " + bucket.name());
                bucketNameList.add(bucket.name());
            }
            return bucketNameList;
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
            return null;
        }
    }

    @Override
    public boolean createBucket(String bucketName) {
        if (null == bucketName || "" == bucketName) return false;
        try {
            // 如存储桶不存在，创建之。
            boolean found = minioClient.bucketExists(bucketName);
            if (found) {
                System.out.println("bucket already exists");
            } else {
                minioClient.makeBucket(bucketName);
                System.out.println("bucket is created successfully");
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
            return false;
        }
    }

    @Override
    public boolean removeBucket(String bucketName) {
        if (null == bucketName || "" == bucketName) return false;
        try {
            // 如存储桶不存在，不执行。
            boolean found = minioClient.bucketExists(bucketName);
            if (found) {
                System.out.println("bucket does not exists");
            } else {
                minioClient.removeBucket(bucketName);
                System.out.println("bucket is removed successfully");
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
            return false;
        }
    }
}

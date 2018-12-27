package com.daoming.fileservice.core.service.impl;

import com.daoming.fileservice.core.service.IFolderOperateService;
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
public class FolderOperateService implements IFolderOperateService {

    @Autowired
    MinioClient minioClient;

    @Override
    public List<String> listRootDirName() {
        try {
            // 列出所有存储桶
            List<Bucket> bucketList = minioClient.listBuckets();
            List<String> bucketNameList = new ArrayList<String>();
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
    public void createRootDir(String dirName) {
        if (null == dirName || "" == dirName) return;
        try {
            // 如存储桶不存在，创建之。
            boolean found = minioClient.bucketExists(dirName);
            if (found) {
                System.out.println("root dir already exists");
            } else {
                minioClient.makeBucket(dirName);
                System.out.println("root dir is created successfully");
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
    }

}

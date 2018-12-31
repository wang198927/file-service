package com.daoming.fileservice.core.service.impl;

import com.daoming.fileservice.core.service.IFileOperateService;
import com.daoming.fileservice.core.service.model.pojo.FolderContentItem;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Item;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
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
    public void uploadFile(String bucketName, String filePath, byte[] byteArray) throws Exception {
        if (null == bucketName || 0 == bucketName.length() || null == filePath || 0 == filePath.length()) return;
        InputStream stream = null;
        try {
            stream = new ByteArrayInputStream(byteArray);
            minioClient.putObject(bucketName, filePath, stream, stream.available(), "application/octet-stream");
            System.out.println("file is uploaded successfully");
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
            throw e;
        } finally {
            stream.close();
        }
    }

    @Override
    public byte[] downloadFile(String bucketName, String filePath) throws Exception {
        InputStream stream = null;
        try {
            minioClient.statObject(bucketName, filePath);
            stream = minioClient.getObject(bucketName, filePath);
            byte[] byteArray = IOUtils.toByteArray(stream);
            return byteArray;
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
            throw e;
        } finally {
            stream.close();
        }
    }

    @Override
    public List<FolderContentItem> listFolderContent(String bucketName, String folderPath) throws Exception {
        List<FolderContentItem> folderContent = new ArrayList<FolderContentItem>();
        if (null == bucketName || 0 == bucketName.length() || null == folderPath || 0 == folderPath.length()) return null;
        try {
            boolean found = minioClient.bucketExists(bucketName);
            if (found) {
                Iterable<Result<Item>> objects = minioClient.listObjects(bucketName, folderPath, false);
                for (Result<Item> result : objects) {
                    Item item = result.get();
                    FolderContentItem folderContentItem = new FolderContentItem();
                    folderContentItem.setObjectName(item.objectName());
                    folderContentItem.setDir(item.isDir());
                    if(!folderContentItem.isDir()){
                        folderContentItem.setLastModified(item.lastModified());
                        folderContentItem.setEtag(item.etag());
                        folderContentItem.setSize(item.objectSize());
                        folderContentItem.setStorageClass(item.storageClass());
                    }
                    folderContent.add(folderContentItem);
                }
            } else {
                System.out.println("bucket does not exist");
            }
            return folderContent;
        }catch (Exception e){
            System.out.println("Error occurred: " + e);
            throw e;
        }

    }
}

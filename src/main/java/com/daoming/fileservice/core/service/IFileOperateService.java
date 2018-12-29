package com.daoming.fileservice.core.service;
import java.io.InputStream;
import java.util.List;

public interface IFileOperateService {
    public void uploadFile(String bucketName, String filePath, byte[] stream);
    public byte[] downloadFile(String bucketName, String filePath);
}

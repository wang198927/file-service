package com.daoming.fileservice.core.service;
import java.io.InputStream;
import java.util.List;

public interface IFileOperateService {
    public void uploadFile(String objectName, byte[] stream);
    public byte[] downloadFile(String objectName);
}

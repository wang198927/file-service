package com.daoming.fileservice.core.service;
import com.daoming.fileservice.core.service.model.pojo.FolderContentItem;

import java.util.List;

public interface IFileOperateService {
    public void uploadFile(String bucketName, String filePath, byte[] stream) throws Exception;
    public byte[] downloadFile(String bucketName, String filePath) throws Exception;
    public List<FolderContentItem> listFolderContent(String bucketName, String folderPath) throws Exception;
}

package com.daoming.fileservice.core.service.model.pojo;

import java.util.Date;

/**
 * @author : daoming.wang
 * @date : 2018/12/30
 */
public class FolderContentItem {
    private String objectName;
    private Date lastModified;
    private String etag;
    private long size;
    private String storageClass;
    private boolean isDir;

    public FolderContentItem(String objectName, Date lastModified, String etag, long size, String storageClass, boolean isDir) {
        this.objectName = objectName;
        this.lastModified = lastModified;
        this.etag = etag;
        this.size = size;
        this.storageClass = storageClass;
        this.isDir = isDir;
    }

    public FolderContentItem() {
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getStorageClass() {
        return storageClass;
    }

    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setDir(boolean dir) {
        isDir = dir;
    }
}

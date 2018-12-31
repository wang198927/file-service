package com.daoming.fileservice.webapi.dto;

import java.util.Date;

/**
 * @author : daoming.wang
 * @date : 2018/12/30
 */
public class FolderContentItemDTO {
    private String objectName;
    private Date lastModified;
    private long size;
    private boolean isDir;

    public FolderContentItemDTO(String objectName, Date lastModified, long size, boolean isDir) {
        this.objectName = objectName;
        this.lastModified = lastModified;
        this.size = size;
        this.isDir = isDir;
    }

    public FolderContentItemDTO() {
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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setDir(boolean dir) {
        isDir = dir;
    }
}

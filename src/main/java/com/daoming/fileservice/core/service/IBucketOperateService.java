package com.daoming.fileservice.core.service;

import java.util.List;

public interface IBucketOperateService {
    public boolean createBucket(String bucketName);
    public List<String> listBucketName();
    public boolean removeBucket(String bucketName);
}

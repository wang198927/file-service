package com.daoming.fileservice.core.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.daoming.fileservice.javaconfig.MinioClientConfig.class)
public class FileOperateServiceTest {

    @Autowired
    private FileOperateService fileOperateService;

    @Test
    public void createRootDir() {
        fileOperateService.createRootDir("javatest3");
    }

    @Test
    public void uploadFile() {
    }

    @Test
    public void downloadFile() {
    }
}
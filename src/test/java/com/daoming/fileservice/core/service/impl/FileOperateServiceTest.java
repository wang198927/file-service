package com.daoming.fileservice.core.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.daoming.fileservice.config.ApplicationConfig.class)
public class FileOperateServiceTest {

    @Autowired
    private FileOperateService fileOperateService;


    @Test
    public void uploadFile() {
    }

    @Test
    public void downloadFile() {
    }

    @Test
    public void listFolderContent() {
    }
}
package com.daoming.fileservice.webapi.controller;

import com.daoming.fileservice.common.ServerResponse;
import com.daoming.fileservice.core.service.IFileOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private IFileOperateService iFileOperateService;

    @ResponseBody
    @PostMapping("/upload_file")
    public ServerResponse uploadFile(@RequestParam(value = "bucketName") String bucketName,
                                     @RequestParam(value = "filePath") String filePath,
                                     @RequestParam("file") MultipartFile[] files) {
        if (null == bucketName || 0 == bucketName.length() || null == filePath || 0 == filePath.length())
            return ServerResponse.createByErrorMessage("参数不合法");
        try {
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                iFileOperateService.uploadFile(bucketName.trim(), (filePath + "/" + fileName).trim(), file.getBytes());
                System.out.println(fileName);
            }
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("上传失败");
        }
        System.out.println(bucketName + "," + filePath);
        return ServerResponse.createBySuccess();
    }

    @ResponseBody
    @PostMapping("/upload_byte")
    public ServerResponse uploadFile(@RequestParam(value = "bucketName") String bucketName,
                                     @RequestParam(value = "filePath") String filePath,
                                     @RequestBody byte[] data) {

        if (null == bucketName || 0 == bucketName.length() || null == filePath || 0 == filePath.length())
            return ServerResponse.createByErrorMessage("参数不合法");
        try {
                iFileOperateService.uploadFile(bucketName.trim(), filePath.trim(), data);
                System.out.println(filePath);
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("上传失败");
        }
        System.out.println(bucketName + "," + filePath);
        return ServerResponse.createBySuccess();
    }
}

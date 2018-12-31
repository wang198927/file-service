package com.daoming.fileservice.webapi.controller;

import com.daoming.fileservice.common.ServerResponse;
import com.daoming.fileservice.core.service.IFileOperateService;
import com.daoming.fileservice.core.service.model.pojo.FolderContentItem;
import com.daoming.fileservice.webapi.dto.FolderContentItemDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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
            return ServerResponse.createByErrorMessage("param invalid");
        try {
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                iFileOperateService.uploadFile(bucketName.trim(), (filePath + "/" + fileName).trim(), file.getBytes());
                System.out.println(fileName);
            }
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage(e.getMessage());
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
            return ServerResponse.createByErrorMessage("param invalid");
        try {
            iFileOperateService.uploadFile(bucketName.trim(), filePath.trim(), data);
            System.out.println(filePath);
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
        System.out.println(bucketName + "," + filePath);
        return ServerResponse.createBySuccess();
    }

    @GetMapping("/download")
    @ResponseBody
    public byte[] downloadFile(@RequestParam(value = "bucketName") String bucketName,
                               @RequestParam(value = "filePath") String filePath) {
        if (null == bucketName || 0 == bucketName.length() || null == filePath || 0 == filePath.length())
            return null;
        try {
            return iFileOperateService.downloadFile(bucketName, filePath);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public ServerResponse listFolderContent(@RequestParam(value = "bucketName") String bucketName,
                                                        @RequestParam(value = "folderPath") String folderPath) {
        if (null == bucketName || 0 == bucketName.length() || null == folderPath || 0 == folderPath.length())
            return null;
        try {
            List<FolderContentItemDTO> folderContentItemDTOList = new ArrayList<FolderContentItemDTO>();
            List<FolderContentItem> folderContentList = iFileOperateService.listFolderContent(bucketName, folderPath);
            for (FolderContentItem folderContentItem : folderContentList) {
                folderContentItem.setObjectName(folderContentItem.getObjectName().replace(folderPath, ""));
                FolderContentItemDTO folderContentItemDTO = new FolderContentItemDTO();
                BeanUtils.copyProperties(folderContentItem, folderContentItemDTO);
                folderContentItemDTOList.add(folderContentItemDTO);
            }
            return ServerResponse.createBySuccess(folderContentItemDTOList);
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
    }
}

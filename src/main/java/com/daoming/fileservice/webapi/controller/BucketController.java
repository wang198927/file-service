package com.daoming.fileservice.webapi.controller;

import com.daoming.fileservice.common.ServerResponse;
import com.daoming.fileservice.core.service.IBucketOperateService;
import com.daoming.fileservice.core.service.IFileOperateService;
import com.daoming.fileservice.core.service.IBucketOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bucket")
public class BucketController {

    @Autowired
    private IBucketOperateService iBucketOperateService;


    @ResponseBody
    @RequestMapping("/list")
    public ServerResponse<List<String>> getAllBucket() {
        List<String> bucketList = iBucketOperateService.listBucketName();
        if (null == bucketList) {
            return ServerResponse.createByErrorMessage("获取存储桶失败");
        }
        return ServerResponse.createBySuccess(bucketList);
    }

    @ResponseBody
    @RequestMapping("/add")
    public ServerResponse createBucket(@RequestParam(value = "bucketName", required = false) String bucketName) {
        return iBucketOperateService.createBucket(bucketName) ? ServerResponse.createBySuccess() : ServerResponse.createByErrorMessage("创建存储桶失败");
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ServerResponse removeBucket(@RequestParam(value = "bucketName", required = false) String bucketName) {
        return iBucketOperateService.removeBucket(bucketName) ? ServerResponse.createBySuccess() : ServerResponse.createByErrorMessage("移除存储桶失败");
    }

//    @ResponseBody
//    @RequestMapping("delete")
//    public ServerResponse createBucket(){
//        return iBucketOperateService.createBucket();
//    }
}

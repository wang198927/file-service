package com.daoming.fileservice.webapi.controller;

import com.daoming.fileservice.common.ServerResponse;
import com.daoming.fileservice.core.service.IFileOperateService;
import com.daoming.fileservice.core.service.IFolderOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/root_dir")
public class DirController {

    @Autowired
    private IFolderOperateService iFolderOperateService;


    @ResponseBody
    @GetMapping("list")
    public ServerResponse<String> getAllRootDirName(){
        return iFolderOperateService.listRootDirName();
    }

    @ResponseBody
    @PutMapping("add")
    public List<String> createRootDir(){
        return iFolderOperateService.createRootDir();
    }
}

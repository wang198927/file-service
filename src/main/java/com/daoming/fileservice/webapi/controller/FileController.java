package com.daoming.fileservice.webapi.controller;

import com.daoming.fileservice.core.service.IFileOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class FileController {

    @Autowired
    private IFileOperateService iFileOperateService;
}
